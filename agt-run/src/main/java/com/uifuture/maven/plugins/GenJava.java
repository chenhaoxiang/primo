/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins;

import com.uifuture.maven.plugins.core.common.BaseConstant;
import com.uifuture.maven.plugins.core.common.ConfigConstant;
import com.uifuture.maven.plugins.core.dto.JavaClassDTO;
import com.uifuture.maven.plugins.core.build.BuildClass;
import com.uifuture.maven.plugins.core.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhx
 * @version GenJava.java, v 0.1 2019-06-24 16:47 chenhx
 */
public class GenJava {

    private static Log log = new SystemStreamLog();

    /**
     *
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
            File file = new File(testJavaName);
            if (file.exists()) {
                log.info(file + "已经存在，不进行生成");
                return;
            }
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                log.error(file.getParentFile() + "生成失败");
                return;
            }

            JavaClassDTO javaClassDTO = new JavaClassDTO();
            javaClassDTO.setDate(BaseConstant.DATE);
            javaClassDTO.setAuthor(ConfigConstant.CONFIG_ENTITY.getAuthor());
            javaClassDTO.setModelNameUpperCamel(className);
            javaClassDTO.setModelNameLowerCamel(StringUtil.strConvertLowerCamel(className));
            //获取类中方法
            if(!BuildClass.build(ConfigConstant.CONFIG_ENTITY.getTestPackageName() + "." + className,javaClassDTO)){
                return;
            }

            Map<String, Object> data = new HashMap<>();
            data.put("javaClassDTO", javaClassDTO);
            //获取mock的类
            cfg.getTemplate(ConfigConstant.CONFIG_ENTITY.getConfigFileName()).process(data, new FileWriter(file));
            log.info(file + "生成成功");
        } catch (Exception e) {
            log.error("生成失败，出现异常", e);
        }

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