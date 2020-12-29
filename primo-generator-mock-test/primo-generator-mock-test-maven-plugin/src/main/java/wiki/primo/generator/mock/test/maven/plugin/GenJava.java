/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.maven.plugin;

import com.alibaba.fastjson.JSON;
import wiki.primo.generator.mock.test.core.build.impl.BuildClassImpl;
import wiki.primo.generator.mock.test.core.constant.CommonConstant;
import wiki.primo.generator.mock.test.core.util.StringUtils;
import wiki.primo.generator.mock.test.core.util.UUIDUtils;
import wiki.primo.generator.mock.test.data.config.json.JsonConfig;
import wiki.primo.generator.mock.test.data.dto.JavaClassDTO;
import wiki.primo.generator.mock.test.data.info.JavaClassInfo;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenhx
 * @version GenJava.java, v 0.1 2019-06-24 16:47 chenhx
 */
public class GenJava {

    private static Log log = new SystemStreamLog();


    public static void main(String[] args) {

        String json = "{\n" +
                "  \"isOpen\": false,\n" +
                "  \"list\":\n" +
                "  [\n" +
                "    {\n" +
                "      \"scope\":\"作用域：全局（global）、包（package）、类（class）、方法（method） - 默认全局\",\n" +
                "      \"scopeValue\": \"作用域的值，global则无需配置该值，package则为包名，class则为类名，method则为方法名\",\n" +
                "      \"type\": \"JavaBean类型（custom）/基础类型（base） - 默认自定义类型\",\n" +
                "      \"name\": \"类型的全限定名称\",\n" +
                "      \"value\":{\n" +
                "        \"若type=base，则该值固定为value\":\"值\",\n" +
                "        \"若type=custom，自定义类型，value下的json为fastjson序列化的json值\":\"值\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" +
                "\n";

        JsonConfig jsonConfig = JSON.parseObject(json, JsonConfig.class);
        System.out.println(jsonConfig.getList().get(0).getValue());

        System.out.println(jsonConfig);

    }

    /**
     * 开始生成测试类入口
     *
     * @param javaClassInfo 参数
     */
    public static void genTest(JavaClassInfo javaClassInfo) {
        try {
            //设置配置文件 freemarker
            Configuration cfg = getConfiguration();

            //已经生成的测试类的方法名称
            Set<String> testMethodNameSet = new HashSet<>();

            //文件是否存在，false-不存在
            boolean fileIsExists = false;

            File testFile = new File(javaClassInfo.getTestAbsolutePath());
            //已经存在文件
            if (testFile.exists()) {
                log.info(testFile + "已经存在，进行类方法追加生成");
                //获取当前测试的类信息
                String testClassName = javaClassInfo.getPackageName() + "." + javaClassInfo.getTypeName() + CommonConstant.TEST_CLASS_SUFFIX;
                JavaClass testJavaClass = CommonConstant.javaProjectBuilder.getClassByName(testClassName);
                //遍历方法
                List<JavaMethod> javaMethodList = testJavaClass.getMethods();
                for (JavaMethod javaMethod : javaMethodList) {
                    testMethodNameSet.add(javaMethod.getName());
                    log.info("获取测试类的方法名称:" + javaMethod.getName());
                }
                log.info("获取的已经生成的类方法名称为:" + javaMethodList + ",测试类：" + testClassName);
                fileIsExists = true;

            } else {
                if (!testFile.getParentFile().exists() && !testFile.getParentFile().mkdirs()) {
                    log.error(testFile.getParentFile() + "文件的路径不存在，本次生成失败");
                    return;
                }
            }

            //构建参数，核心
            JavaClassDTO javaClassDTO = BuildClassImpl.build(javaClassInfo);

            //获取类中方法
            if (javaClassDTO == null) {
                return;
            }

            Map<String, Object> data = new HashMap<>(2);
            data.put("javaClassDTO", javaClassDTO);

            //获取mock的类
            if (!fileIsExists) {
                //文件不存在,进行初始化生成
                cfg.getTemplate(CommonConstant.CONFIG_ENTITY.getConfigFileName()).process(data, new FileWriter(testFile));
                log.info(testFile + "生成成功");
            } else {
                //文件已经存在，进行追加方法
                File newFile = fileIsExists(javaClassInfo.getTypeName(), cfg, testMethodNameSet, testFile, javaClassDTO, data, javaClassInfo);
                if (newFile == null) {
                    log.error(javaClassInfo.getFullyTypeName()+" 追加方法失败");
                    return;
                }
                log.info(testFile + ", 追加方法成功，生成的临时文件:" + newFile);
            }
        } catch (Exception e) {
            log.error("生成失败，出现异常", e);
        }

    }

    /**
     * 文件存在时，进行追加生成的方法
     * 生成一个临时文件，将临时文件中多出的方法写入到原来的文件中
     * @param className         类名
     * @param configuration     Template模板生成文件
     * @param testMethodNameSet 已有测试方法的名称
     * @param file              原有已生成的文件
     * @param javaClassDTO      生成的临时文件信息
     * @param data              模板的数据
     * @param javaClassInfo     类通用信息
     * @return 临时生成的文件
     * @throws TemplateException 模板异常
     * @throws IOException       流异常
     */
    private static File fileIsExists(String className, Configuration configuration, Set<String> testMethodNameSet, File file, JavaClassDTO javaClassDTO, Map<String, Object> data, JavaClassInfo javaClassInfo) throws TemplateException, IOException {
        String testJavaName;//测试类已经存在了
        String randId = UUIDUtils.getID();
        testJavaName = CommonConstant.CONFIG_ENTITY.getBasedir() + CommonConstant.JAVA_TEST_SRC + javaClassInfo.getPackageName().replace(".", "/") + "/" + className + randId + CommonConstant.TEST_CLASS_SUFFIX + ".java";

        javaClassDTO.setModelNameUpperCamelTestClass(className + randId + CommonConstant.TEST_CLASS_SUFFIX);
        javaClassDTO.setModelNameLowerCamelTestClass(StringUtils.strConvertLowerCamel(className + randId + CommonConstant.TEST_CLASS_SUFFIX));

        File newFile = new File(testJavaName);
        if (!newFile.getParentFile().exists() && !newFile.getParentFile().mkdirs()) {
            log.error(newFile.getParentFile() + "生成失败，请检查是否有权限");
            return null;
        }
        configuration.getTemplate(CommonConstant.CONFIG_ENTITY.getConfigFileName()).process(data, new FileWriter(newFile));
        //读取类的方法
        String newClassName = javaClassInfo.getPackageName() + "." + className + randId + CommonConstant.TEST_CLASS_SUFFIX;
        log.info("获取临时生成的类名:" + newClassName);
        //读取包下所有的测试的java类文件
        CommonConstant.javaProjectBuilder.addSourceTree(newFile);

        JavaClass testJavaClass = CommonConstant.javaProjectBuilder.getClassByName(newClassName);
        List<JavaMethod> javaMethodList = testJavaClass.getMethods();
        log.info("获取的方法名称:" + javaMethodList);
        for (JavaMethod javaMethod : javaMethodList) {
            if (!testMethodNameSet.contains(javaMethod.getName())) {
                //新增的方法 - 测试方法的源码
                String code = javaMethod.getSourceCode();
                log.debug("获取追加的方法源码为:" + code);
                //原来的文件进行追加方法
                String methodStr = "\n    @Test\n" +
                        "    public void " + javaMethod.getName() + "(){\n" +
                        code + "\n" +
                        "    }\n}";
                //文件追加
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String lineContent = null;
                List<String> oldFileStr = new ArrayList<>();
                while ((lineContent = br.readLine()) != null) {
                    oldFileStr.add(lineContent);
                }
                br.close();
                fileReader.close();
                //文件覆盖
                for (int i = oldFileStr.size() - 1; i >= 0; i--) {
                    String line = oldFileStr.get(i).trim();
                    if (StringUtils.isEmpty(line)) {
                        continue;
                    }
                    if ("}".equals(line)) {
                        //删除该行
                        oldFileStr.remove(i);
                        break;
                    }
                    if (line.endsWith("}")) {
                        //删除该行最后一个}字符
                        oldFileStr.set(i, oldFileStr.get(i).substring(0, oldFileStr.get(i).lastIndexOf("}")));
                        break;
                    }
                }

                StringBuilder fileStr = new StringBuilder();
                //覆盖保存文件
                for (String line : oldFileStr) {
                    fileStr.append(line).append("\n");
                }
                fileStr.append(methodStr);
                //写入文件
                PrintStream ps = new PrintStream(new FileOutputStream(file));
                // 往文件里写入字符串
                ps.println(fileStr);
                ps.close();
            }
            log.info("获取临时测试类的方法名称:" + javaMethod.getName());
        }
        //删除文件
        if (!newFile.delete()) {
            log.error("删除临时文件失败，请检查是否有权限。文件:" + newFile);
        }
        return newFile;
    }

    /**
     * 生成freemarker引擎配置
     *
     * @return
     * @throws IOException
     */
    private static Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        //模板文件
        File file = new File(CommonConstant.CONFIG_ENTITY.getBasedir().getPath() + CommonConstant.CONFIG_ENTITY.getConfigPath());
        cfg.setDirectoryForTemplateLoading(file);
        //设置文件编码
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

}
