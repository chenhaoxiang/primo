package wiki.primo.generator.mybatis.plus;


import wiki.primo.generator.mybatis.plus.config.constant.ConfigConstant;
import wiki.primo.generator.mybatis.plus.config.constant.ConstVal;
import wiki.primo.generator.mybatis.plus.config.builder.ConfigBuilder;
import wiki.primo.generator.mybatis.plus.config.po.TableInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 生成文件
 *
 * @author chenhx
 * @since 2020/8/30
 */
@Mojo(name = "code", threadSafe = true)
public class GenerateMojo extends AbstractGenerateMojo {

    /**
     * velocity引擎
     */
    private VelocityEngine engine;

    /**
     * 输出文件
     */
    private Map<String, String> outputFiles;

    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info("==========================准备生成文件...==========================");
        try {
            // 初始化配置
            initConfig();
            // 初始化输出文件路径模板
            initOutputFiles();
            // 创建输出文件路径
            mkdirs(config.getPathInfo());
            // 获取上下文
            Map<String, VelocityContext> ctxData = analyzeData(config);
            // 循环生成文件
            for (Map.Entry<String, VelocityContext> ctx : ctxData.entrySet()) {
                batchOutput(ctx.getKey(), ctx.getValue());
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
     * 分析数据
     *
     * @param config 总配置信息
     * @return 解析数据结果集
     */
    private Map<String, VelocityContext> analyzeData(ConfigBuilder config) {
        List<TableInfo> tableList = config.getTableInfoList();
        Map<String, String> packageInfo = config.getPackageInfo();
        Map<String, VelocityContext> ctxData = new HashMap<String, VelocityContext>();
        String superEntityClass = getSuperClassName(config.getSuperEntityClass());
        String superMapperClass = getSuperClassName(config.getSuperMapperClass());
        String superServiceClass = getSuperClassName(config.getSuperServiceClass());
        String superServiceImplClass = getSuperClassName(config.getSuperServiceImplClass());
        String superControllerClass = getSuperClassName(config.getSuperControllerClass());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        /**
         * 设置vm中的值
         */
        for (TableInfo tableInfo : tableList) {
            VelocityContext ctx = new VelocityContext();
            ctx.put("package", packageInfo);
            ctx.put("table", tableInfo);
            ctx.put("entity", tableInfo.getEntityName());
            ctx.put("addTabeName", !tableInfo.getEntityName().toLowerCase().equals(tableInfo.getName().toLowerCase()));
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
            File dir = new File(entry.getValue());
            if (!dir.exists()) {
                boolean result = dir.mkdirs();
                if (result) {
                    log.info("创建目录： [" + entry.getValue() + "]成功");
                }
            }
        }
    }

    /**
     * 初始化输出目录
     */
    private void initOutputFiles() {
        outputFiles = new HashMap<String, String>();
        Map<String, String> pathInfo = config.getPathInfo();
        for (ConfigConstant constant : ConstVal.configConstantList) {
            outputFiles.put(constant.getPackageInfoKey(), pathInfo.get(constant.getPathInfoKey()) + constant.getOutputFilesRuleValue());
        }
    }

    /**
     * 合成上下文与模板
     *
     * @param context vm上下文
     */
    private void batchOutput(String entityName, VelocityContext context) {
        try {
            for (ConfigConstant constant : ConstVal.configConstantList) {
                String file = String.format(outputFiles.get(constant.getPackageInfoKey()), entityName);
                if (isCreate(file)) {
                    vmToFile(context, constant.getTemplatePath(), file);
                }
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

}
