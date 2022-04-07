package wiki.primo.generator.mybatis.plus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;
import wiki.primo.generator.mybatis.plus.builder.page.ControllerMenuBuilder;


import wiki.primo.generator.mybatis.plus.builder.ConfigBuilder;
import wiki.primo.generator.mybatis.plus.builder.page.ControllerPageBuilder;
import wiki.primo.generator.mybatis.plus.builder.page.PageFieldBuilder;
import wiki.primo.generator.mybatis.plus.builder.po.TableFieldPO;
import wiki.primo.generator.mybatis.plus.config.constant.ConfigConstant;
import wiki.primo.generator.mybatis.plus.config.constant.ConstVal;
import wiki.primo.generator.mybatis.plus.builder.po.TableInfoPO;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import wiki.primo.generator.mybatis.plus.util.FileUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生成文件
 *
 * @author chenhx
 * @since 2020/8/30
 */
@Mojo(name = "code", threadSafe = true)
public class GenerateMojo extends AbstractGenerateMojo {

    private ConfigBuilder config;
    /**
     * velocity引擎
     */
    private VelocityEngine engine;

    /**
     * 输出文件
     */
    private Map<String, String> classOutputFiles;

    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info("==========================准备生成文件...==========================");
        try {
            // 初始化配置
            config = initConfig();
            // 初始化输出文件路径模板，需要遍历list
            initOutputFiles();
            // 创建输出文件路径
            mkdirs(config.getPathInfo());
            // 获取上下文 - 初始化每个表的数据
            Map<String, VelocityContext> ctxData = loadJavaData(config);
            // 遍历表
            for (Map.Entry<String, VelocityContext> ctx : ctxData.entrySet()) {
                batchOutput(ctx.getKey(), ctx.getValue(),ConstVal.configConstantList);
            }
            //获取通用的上下文,这里有优化空间
            VelocityContext velocityContext = commonData(config);
            //生成一次的文件
            batchOutput("",velocityContext,ConstVal.oneConfigConstantList);

            // 获取上下文 - 初始化每个页面需要的的数据
            List<ControllerPageBuilder> controllerPageBuilders = loadPageData(config);
            //生成文件
            batchOutput(controllerPageBuilders);

            //获取jar包下class 下的所有文件 参考mybatis注解扫描类@MapperScan来实现
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "template/page/static/**/*");
            log.info("获取的resources静态文件数量:" + resources.length);
            for (Resource resource : resources) {
                //下载到本地
                File resourceFile = resource.getFile();
                String resourcePath = resourceFile.getPath();

                //创建文件路径
                String saveDir = "src"+ File.separator +"resources" + File.separator + resourcePath.substring(resourcePath.indexOf("template/page/"));
                createPath(saveDir);
                //得到输入流
                InputStream inputStream = resource.getInputStream();
                //获取自己数组
                byte[] getData = FileUtils.readInputStream(inputStream);

                File file = new File(saveDir + File.separator + resource.getFilename());
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(getData);
                fos.close();
                inputStream.close();
                log.info("下载静态文件【"+resourcePath+"】成功，保存路径：" + saveDir + ",文件名：" + resource.getFilename());
            }

            // 打开输出目录
            if (isOpen()) {
                try {
                    String osName = System.getProperty("os.name");
                    if (osName != null) {
                        if (osName.contains("Mac")) {
                            Runtime.getRuntime().exec("open " + getOutputDir());
                        } else if (osName.contains("Windows")) {
                            Runtime.getRuntime().exec("cmd /c start " + getOutputDir());
                        } else {
                            log.info("文件输出目录:" + getOutputDir());
                        }
                    }
                } catch (IOException e) {
                    log.error("[GenerateMojo->execute]打开输出目录异常", e);
                }
            }
            log.info("==========================文件生成完成！！！==========================");
        } catch (Exception e) {
            log.error("==========================文件生成异常！！！==========================", e);
        }
    }

    /**
     * 初始化每个页面需要的的数据
     * @param config
     * @return
     */
    private List<ControllerPageBuilder> loadPageData(ConfigBuilder config) {
        List<ControllerPageBuilder> controllerPageBuilders = new ArrayList<>();
        List<TableInfoPO> tableList = config.getTableInfoList();
        for (TableInfoPO tableInfo : tableList) {
            ControllerPageBuilder controllerPageBuilder = new ControllerPageBuilder();
            ControllerMenuBuilder controllerMenuBuilder = new ControllerMenuBuilder();
            controllerMenuBuilder.setName(tableInfo.getName());
            controllerMenuBuilder.setUrl("/"+TableInfoPO.strConvertLowerCamel(tableInfo.getEntityName())+"/table");
//            controllerPageBuilder.setControllerUrlBuilder(controllerUrlBuilder);
            controllerPageBuilder.setTemplateFilePath(ConstVal.TEMPLATE_PAGE_TABLE);
            controllerPageBuilder.setSaveFilePath(getOutputResourcesDir() + "template" + File.separator + "tables"  + File.separator);
            controllerPageBuilder.setSaveFilePathName(TableInfoPO.strConvertLowerCamel(tableInfo.getEntityName()) + ".ftl");
            controllerPageBuilder.setControllerMenuBuilder(controllerMenuBuilder);
            controllerPageBuilder.setTableInfoPO(tableInfo);

            //设置字段信息
            List<PageFieldBuilder> fieldResps = new ArrayList<>();
            for (TableFieldPO field : tableInfo.getFields()) {
                PageFieldBuilder pageFieldBuilder = new PageFieldBuilder();
                pageFieldBuilder.setJavaName(field.getPropertyName());
                pageFieldBuilder.setJavaType(field.getPropertyType());
                pageFieldBuilder.setFieldName(field.getName());
                pageFieldBuilder.setFieldType(field.getType());
                pageFieldBuilder.setFieldDescribe(field.getComment());
                pageFieldBuilder.setMajorKey(field.isKeyFlag());
                pageFieldBuilder.setCanFuzzy(false);
                //暂时定，对于varchar 128以下的字段开启模糊查询
                if(field.getType().startsWith("varchar")) {
                    Integer length = getMaxLength(field.getType());
                    if(length>0 && length<=128) {
                        pageFieldBuilder.setCanFuzzy(true);
                    }
                }
                pageFieldBuilder.setMaxLength(getMaxLength(field.getType()));
                fieldResps.add(pageFieldBuilder);
            }
            controllerPageBuilder.setFieldResps(fieldResps);
            controllerPageBuilders.add(controllerPageBuilder);
        }
        return controllerPageBuilders;
    }

    /**
     * 根据数据库字段类型获取最大的长度
     * @param jdbcType
     * @return
     */
    public Integer getMaxLength(String jdbcType){
        if(StringUtils.isEmpty(jdbcType)){
            return 0;
        }
        if(jdbcType.startsWith("json")){
            return 102400;
        }
        if(jdbcType.startsWith("text")){
            return 102400;
        }
        if(jdbcType.startsWith("datetime")){
            return 32;
        }
        Integer length = wiki.primo.generator.mybatis.plus.util.StringUtils.getNumber(jdbcType);
        if(length==null){
            log.warn("未匹配到具体的数据长度，可对维护者进行反馈。jdbcType="+jdbcType);
            return 0;
        }
        return length;
    }

    /**
     * 分析数据，生成Java类所需要的数据
     *
     * @param config 总配置信息
     * @return 解析数据结果集
     */
    private Map<String, VelocityContext> loadJavaData(ConfigBuilder config) {
        List<TableInfoPO> tableList = config.getTableInfoList();
        Map<String, String> packageInfo = config.getPackageInfo();
        Map<String, VelocityContext> ctxData = new HashMap<String, VelocityContext>();
        String superEntityClass = getSuperClassName(config.getSuperEntityClass());
        String superMapperClass = getSuperClassName(config.getSuperMapperClass());
        String superServiceClass = getSuperClassName(config.getSuperServiceClass());
        String superServiceImplClass = getSuperClassName(config.getSuperServiceImplClass());
        String superControllerClass = getSuperClassName(config.getSuperControllerClass());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        /**
         * 设置vm中的值
         */
        for (TableInfoPO tableInfo : tableList) {
            VelocityContext ctx = new VelocityContext();
            ctx.put("package", packageInfo);
            ctx.put("table", tableInfo);
            ctx.put("entity", tableInfo.getEntityName());
            ctx.put("addTableName", !tableInfo.getEntityName().toLowerCase().equals(tableInfo.getName().toLowerCase()));
            ctx.put("idGenType", config.getIdType());
            ctx.put("superEntityClassPackage", config.getSuperEntityClass());
            ctx.put("superEntityClass", superEntityClass);
            ctx.put("superMapperClassPackage", config.getSuperMapperClass());
            ctx.put("superMapperClass", superMapperClass);
            ctx.put("superServiceClassPackage", config.getSuperServiceClass());
            ctx.put("superServiceClass", superServiceClass);
            ctx.put("superServiceImplClassPackage", config.getSuperServiceImplClass());
            ctx.put("superServiceImplClass", superServiceImplClass);
            ctx.put("superControllerClassPackage", config.getSuperControllerClass());
            ctx.put("superControllerClass", superControllerClass);
            ctx.put("enableCache", isEnableCache());
            ctx.put("author", getAuthor());
            ctx.put("activeRecord", isActiveRecord());
            ctx.put("date", date);
            ctxData.put(tableInfo.getEntityName(), ctx);
        }
        return ctxData;
    }

    /**
     * 一些通用的设置
     * @param config
     * @return
     */
    private VelocityContext commonData(ConfigBuilder config) {
        Map<String, String> packageInfo = config.getPackageInfo();
        String superEntityClass = getSuperClassName(config.getSuperEntityClass());
        String superMapperClass = getSuperClassName(config.getSuperMapperClass());
        String superServiceClass = getSuperClassName(config.getSuperServiceClass());
        String superServiceImplClass = getSuperClassName(config.getSuperServiceImplClass());
        String superControllerClass = getSuperClassName(config.getSuperControllerClass());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        VelocityContext ctx = new VelocityContext();
        ctx.put("package", packageInfo);
        ctx.put("idGenType", config.getIdType());
        ctx.put("superEntityClassPackage", config.getSuperEntityClass());
        ctx.put("superEntityClass", superEntityClass);
        ctx.put("superMapperClassPackage", config.getSuperMapperClass());
        ctx.put("superMapperClass", superMapperClass);
        ctx.put("superServiceClassPackage", config.getSuperServiceClass());
        ctx.put("superServiceClass", superServiceClass);
        ctx.put("superServiceImplClassPackage", config.getSuperServiceImplClass());
        ctx.put("superServiceImplClass", superServiceImplClass);
        ctx.put("superControllerClassPackage", config.getSuperControllerClass());
        ctx.put("superControllerClass", superControllerClass);
        ctx.put("enableCache", isEnableCache());
        ctx.put("author", getAuthor());
        ctx.put("activeRecord", isActiveRecord());
        ctx.put("date", date);
        return ctx;
    }

    /**
     * 获取类名
     *
     * @param classPath
     * @return
     */
    private String getSuperClassName(String classPath) {
        if (StringUtils.isBlank(classPath)) {
            return null;
        }
        return classPath.substring(classPath.lastIndexOf(".") + 1);
    }

    /**
     * 处理输出目录
     *
     * @param pathInfo 路径信息
     */
    private void mkdirs(Map<String, String> pathInfo) {
        for (Map.Entry<String, String> entry : pathInfo.entrySet()) {
            createPath(entry.getValue());
        }
    }

    /**
     * 创建目录
     * @param value
     */
    private void createPath(String value) {
        File dir = new File(value);
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            if (result) {
                log.info("创建目录： [" + value + "]成功");
            }
        }
    }

    /**
     * 初始化输出目录，遍历configList
     */
    private void initOutputFiles() {
        classOutputFiles = new HashMap<String, String>();
        Map<String, String> pathInfo = config.getPathInfo();
        for (List<ConfigConstant> constants : ConstVal.getConfigConstantList()) {
            for (ConfigConstant constant : constants) {
                //通过key获取输出路径
                classOutputFiles.put(constant.getPackageInfoKey(), pathInfo.get(constant.getPathInfoKey()) + constant.getOutputFilesRuleValue());
            }
        }
    }

    /**
     * 合成上下文与模板 表的生成
     * @param context vm上下文
     */
    private void batchOutput(String entityName, VelocityContext context,List<ConfigConstant> constants) {
        try {
            for (ConfigConstant constant : constants) {
                String file = String.format(classOutputFiles.get(constant.getPackageInfoKey()), entityName);
                //是否覆盖 - 全局的判断
                if (!isCreate(file)) {
                    continue;
                }
                //扩展类不进行覆盖，强制,进行判断独立的开关
                if (!isCreate(file,constant)) {
                    continue;
                }

                vmToFile(context, constant.getTemplatePath(), file);
            }
        } catch (IOException e) {
            log.error("无法创建文件，请检查配置信息！", e);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("创建文件出现异常，请检查配置信息！", e);
            e.printStackTrace();
        }
    }
    private void batchOutput(List<ControllerPageBuilder> controllerPageBuilders) {
        try {
            //获取table
            for (ControllerPageBuilder controllerPageBuilder : controllerPageBuilders) {
                //目录是否存在判断
                createPath(controllerPageBuilder.getSaveFilePath());

                String file = controllerPageBuilder.getSaveFilePath() + controllerPageBuilder.getSaveFilePathName();
                //是否覆盖 - 全局的判断
                if (!isCreate(file)) {
                    continue;
                }
                String temp = controllerPageBuilder.getTemplateFilePath();
                VelocityContext context = new VelocityContext();
                context.put("tablesData",controllerPageBuilder);
                vmToFile(context, temp, file);
            }
        } catch (IOException e) {
            log.error("无法创建文件，请检查配置信息！", e);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("创建文件出现异常，请检查配置信息！", e);
            e.printStackTrace();
        }
    }
    /**
     * 将模板转化成为文件
     *
     * @param context      内容对象
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    private void vmToFile(VelocityContext context, String templatePath, String outputFile) throws Exception {
        VelocityEngine velocity = getVelocityEngine();
        Template template = velocity.getTemplate(templatePath, ConstVal.UTF8);
        FileOutputStream fos = new FileOutputStream(outputFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
        template.merge(context, writer);
        writer.close();
        log.info("模板:" + templatePath + ";  文件:" + outputFile);
    }

    /**
     * 设置模版引擎，主要指向获取模版路径
     */
    private VelocityEngine getVelocityEngine() throws Exception {
        if (engine == null) {
            Properties p = new Properties();
            p.setProperty(ConstVal.VM_LOADPATH_KEY, ConstVal.VM_LOADPATH_VALUE);
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
            p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
            p.setProperty(Velocity.INPUT_ENCODING, ConstVal.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, ConstVal.UTF8);
            p.setProperty("file.resource.loader.unicode", "true");
            engine = new VelocityEngine(p);
        }
        return engine;
    }

    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    private boolean isCreate(String filePath) {
        File file = new File(filePath);
        return !file.exists() || isFileOverride();
    }

    private boolean isCreate(String filePath,ConfigConstant constant) {
        File file = new File(filePath);
        return !file.exists() || constant.getFileOverride();
    }

}
