/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.uifuture.maven.plugins.core.build.BuildClass;
import com.uifuture.maven.plugins.core.common.BaseConstant;
import com.uifuture.maven.plugins.core.common.ConfigConstant;
import com.uifuture.maven.plugins.core.dto.JavaClassDTO;
import com.uifuture.maven.plugins.core.util.StringUtil;
import com.uifuture.maven.plugins.core.util.UUIDUtil;
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

    /**
     * 开始生成测试类入口
     * @param javaName 当前需要生成测试类的类全限定名称
     */
    public static void genTest(String javaName) {
        try {
            //类名
            String className = javaName.substring(javaName.lastIndexOf("/") + 1, javaName.lastIndexOf("."));

            //设置配置文件
            Configuration cfg = getConfiguration();

            String testJavaName = ConfigConstant.CONFIG_ENTITY.getBasedir() + BaseConstant.JAVA_TEST_SRC + ConfigConstant.CONFIG_ENTITY.getTestPackageName().replace(".", "/") + "/" + className + "Test.java";
            log.info("生成的文件路径：" + testJavaName + ", className:" + className);

            //已经生成的测试类的方法名称
            Set<String> testMethodNameSet = new HashSet<>();
            boolean fileIsExists = false;

            File file = new File(testJavaName);
            //已经存在文件
            if (file.exists()) {
                log.info(file + "已经存在，进行类方法追加生成");
                //获取当前测试的类信息
                String testClassName = ConfigConstant.CONFIG_ENTITY.getTestPackageName() + "." + className + "Test";
                JavaClass testJavaClass = BaseConstant.javaProjectBuilder.getClassByName(testClassName);
                //遍历方法
                List<JavaMethod> javaMethodList = testJavaClass.getMethods();
                for (JavaMethod javaMethod : javaMethodList) {
                    testMethodNameSet.add(javaMethod.getName());
                    log.info("获取测试类的方法名称:" + javaMethod.getName());
                }
                log.info("获取的已经生成的类方法名称为:" + javaMethodList + ",测试类：" + testClassName);
                fileIsExists = true;

            } else {
                if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                    log.error(file.getParentFile() + "生成失败");
                    return;
                }
            }

            JavaClassDTO javaClassDTO = new JavaClassDTO();
            javaClassDTO.setDate(BaseConstant.DATE);
            javaClassDTO.setAuthor(ConfigConstant.CONFIG_ENTITY.getAuthor());
            javaClassDTO.setModelNameUpperCamel(className);
            javaClassDTO.setModelNameLowerCamel(StringUtil.strConvertLowerCamel(className));
            javaClassDTO.setModelNameUpperCamelTestClass(className + "Test");
            javaClassDTO.setModelNameLowerCamelTestClass(StringUtil.strConvertLowerCamel(className + "Test"));
            //获取类中方法
            if(!BuildClass.build(ConfigConstant.CONFIG_ENTITY.getTestPackageName() + "." + className,javaClassDTO)){
                return;
            }

            Map<String, Object> data = new HashMap<>(2);
            data.put("javaClassDTO", javaClassDTO);
            //获取mock的类
            if (!fileIsExists) {
                cfg.getTemplate(ConfigConstant.CONFIG_ENTITY.getConfigFileName()).process(data, new FileWriter(file));
                log.info(file + "生成成功");
            } else {
                File newFile = fileIsExists(className, cfg, testMethodNameSet, file, javaClassDTO, data);
                if (newFile == null) {
                    log.error("追加方法失败");
                    return;
                }
                log.info(file + ", 追加方法成功，生成的临时文件:" + newFile);
            }
        } catch (Exception e) {
            log.error("生成失败，出现异常", e);
        }

    }

    /**
     * 文件存在时，进行追加生成的方法
     *
     * @param className         类名
     * @param configuration     Template模板生成文件
     * @param testMethodNameSet 已有测试方法的名称
     * @param file              原有已生成的文件
     * @param javaClassDTO      生成的临时文件信息
     * @param data              模板的数据
     * @return 临时生成的文件
     * @throws TemplateException 模板异常
     * @throws IOException       流异常
     */
    private static File fileIsExists(String className, Configuration configuration, Set<String> testMethodNameSet, File file, JavaClassDTO javaClassDTO, Map<String, Object> data) throws TemplateException, IOException {
        String testJavaName;//测试类已经存在了
        String randId = UUIDUtil.getID();
        testJavaName = ConfigConstant.CONFIG_ENTITY.getBasedir() + BaseConstant.JAVA_TEST_SRC + ConfigConstant.CONFIG_ENTITY.getTestPackageName().replace(".", "/") + "/" + className + randId + "Test.java";

        javaClassDTO.setModelNameUpperCamelTestClass(className + randId + "Test");
        javaClassDTO.setModelNameLowerCamelTestClass(StringUtil.strConvertLowerCamel(className + randId + "Test"));

        File newFile = new File(testJavaName);
        if (!newFile.getParentFile().exists() && !newFile.getParentFile().mkdirs()) {
            log.error(newFile.getParentFile() + "生成失败，请检查是否有权限");
            return null;
        }
        configuration.getTemplate(ConfigConstant.CONFIG_ENTITY.getConfigFileName()).process(data, new FileWriter(newFile));
        //读取类的方法
        String newClassName = ConfigConstant.CONFIG_ENTITY.getTestPackageName() + "." + className + randId + "Test";
        log.info("获取临时生成的类名:" + newClassName);
        //读取包下所有的测试的java类文件
        BaseConstant.javaProjectBuilder.addSourceTree(newFile);

        JavaClass testJavaClass = BaseConstant.javaProjectBuilder.getClassByName(newClassName);
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
                    if (StringUtil.isEmpty(line)) {
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


    private static Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        ConfigConstant.CONFIG_ENTITY.setConfigPath(StringUtil.addSeparator(ConfigConstant.CONFIG_ENTITY.getConfigPath()));
        File file = new File(ConfigConstant.CONFIG_ENTITY.getBasedir() + ConfigConstant.CONFIG_ENTITY.getConfigPath());
        if (!file.exists()) {
            log.error(file + "配置文件不存在");
            throw new RuntimeException("配置文件不存在");
        }
        cfg.setDirectoryForTemplateLoading(file);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }



}