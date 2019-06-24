/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins;

import com.uifuture.maven.plugins.base.AbstractPlugin;
import com.uifuture.maven.plugins.core.common.BaseConstant;
import com.uifuture.maven.plugins.core.common.ConfigConstant;
import com.uifuture.maven.plugins.core.dto.JavaClassDTO;
import com.uifuture.maven.plugins.core.util.JavaProjectBuilderUtil;
import com.uifuture.maven.plugins.core.util.PackageUtil;
import com.uifuture.maven.plugins.core.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建自动化测试
 * @author chenhx
 * @version UnittestPlugin.java, v 0.1 2019-05-30 17:47 chenhx
 */
@Mojo(name="test")
public class UnittestPlugin extends AbstractPlugin {
    /**
     * 需要测试的项目包名
     */
    @Parameter
    private String testPackageName;
    /**
     * 作者
     */
    @Parameter(defaultValue = "")
    private String author;
    /**
     * 是否获取子包下的类
     */
    @Parameter(defaultValue = "true")
    private Boolean childPackage;

    /**
     * 需要mock掉的包，包下所有类的方法都会被mock
     */
    @Parameter
    private String mockPackage;
    /**
     * 不需要初始化默认值的类的包名
     * 在参数初始化的时候，进行赋值为null的类，在该包下的类不会进行赋值
     */
    @Parameter
    private String skipPackages;

    /**
     * 其他的项目名称，一个项目下多个项目模块。项目模块的路径
     */
    @Parameter
    private String otherProjectName;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //设置配置值
        super.execute();
        ConfigConstant.CONFIG_ENTITY.setTestPackageName(testPackageName);
        ConfigConstant.CONFIG_ENTITY.setAuthor(author);
        ConfigConstant.CONFIG_ENTITY.setChildPackage(childPackage);
        ConfigConstant.CONFIG_ENTITY.setMockPackage(mockPackage);
        ConfigConstant.CONFIG_ENTITY.setSkipPackages(skipPackages);
        ConfigConstant.CONFIG_ENTITY.setOtherProjectName(otherProjectName);

        getLog().info( "开始生成自动化测试代码" +
                "\n"+ ConfigConstant.CONFIG_ENTITY
        );

        if(StringUtil.isNotEmpty(skipPackages)) {
            //无法进行预加载的类，设置参数值为null
            Collections.addAll(BaseConstant.skipPackage, skipPackages.split(";"));
        }

        //获取该包下所有的类
        List<String> javaList = PackageUtil.getClassName(basedir.getPath(), testPackageName, childPackage);
        getLog().info( "获取的所有类名为："+javaList);

        //初始化类源
        initJavaProjectBuilder();

        //class文件绝对路径
        for (String javaName : javaList) {
            getLog().info("当前文件路径："+javaName);
            if(javaName.endsWith("$1")){
                //跳过
                getLog().info("跳过:"+javaName);
                continue;
            }
            //文件路径
            genTest(javaName);
        }
    }

    /**
     * 初始化类源
     */
    private void initJavaProjectBuilder() {
        // 获取类库
//        JavaProjectBuilder builder = new JavaProjectBuilder();
        // 正在读取单个源文件
//        builder.addSource(new File(javaNameFile));
        //读取包下所有的java类文件
        String mainJava = basedir.getPath() + BaseConstant.JAVA_MAIN_SRC;
        BaseConstant.javaProjectBuilder.addSourceTree(new File(mainJava));
        getLog().info("加载当前模块的类："+mainJava);

        //加载其他模块的类
        if(StringUtil.isNotEmpty(otherProjectName)){
            for (String name : otherProjectName.split(";")) {
                String fileName = basedir.getPath().substring(0,basedir.getPath().lastIndexOf("/")+1)+name + BaseConstant.JAVA_MAIN_SRC;
                BaseConstant.javaProjectBuilder.addSourceTree(new File(fileName));
                getLog().info("加载其他模块的类："+fileName);
            }
        }

        if(StringUtil.isNotEmpty(mockPackage)) {
            String mockJava = basedir + BaseConstant.JAVA_MAIN_SRC + mockPackage.replace(".", "/");
            //获取包下所有的类
            List<String> javaList = PackageUtil.getClassNameByFile(mockJava, true);
            for (String path : javaList) {
                path = path.replace("/", ".");
                path = path.substring(path.indexOf(mockPackage), path.lastIndexOf("."));
                BaseConstant.mockJavaSet.add(path);
            }
            getLog().info("需要mock的所有类：" + BaseConstant.mockJavaSet);
        }
    }


    private void genTest(String javaName) {
        try {
            //类名
            String className = javaName.substring(javaName.lastIndexOf("/")+1,javaName.lastIndexOf("."));

            Configuration cfg = getConfiguration();

            String testJavaName =basedir + BaseConstant.JAVA_TEST_SRC + testPackageName.replace(".", "/")+ "/"+className +"Test.java";
            getLog().info("生成的文件路径："+testJavaName+", className:"+className);
            File file = new File(testJavaName);
            if (file.exists()) {
                //TODO 已经存在，不处理
                getLog().info(file+"已经存在，不进行生成");
                return;
            }
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                getLog().error(file.getParentFile()+"生成失败" );
                return;
            }

            Map<String, Object> data = new HashMap<>();
            data.put("date", BaseConstant.DATE);
            data.put("author", author);
            data.put("modelNameUpperCamel", className);
            data.put("modelNameLowerCamel", StringUtil.strConvertLowerCamel(className));

            //获取类中方法
            JavaClassDTO javaClassDTO = JavaProjectBuilderUtil.buildTestMethod(javaName,testPackageName+"."+className);
            data.put("javaClassDTO", javaClassDTO);

            //获取mock的类
            cfg.getTemplate(configFileName).process(data, new FileWriter(file));

            getLog().info(file+"生成成功");
        } catch (Exception e) {
            getLog().error("生成失败，出现异常",e);
        }

    }


    private Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        configPath = StringUtil.addSeparator(configPath);
        File file = new File(basedir + configPath);
        if(!file.exists()){
            getLog().error(file+"配置文件不存在");
            throw new RuntimeException("配置文件不存在");
        }
        cfg.setDirectoryForTemplateLoading(file);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }



}