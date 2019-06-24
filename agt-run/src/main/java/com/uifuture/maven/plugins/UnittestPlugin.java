/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
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
 *
 * @author chenhx
 * @version UnittestPlugin.java, v 0.1 2019-05-30 17:47 chenhx
 */
@Mojo(name = "test")
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

    public static void main(String[] args) throws IOException {
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSource(new File("/Users/chenhx/Desktop/github/maven-auto-generate-test-plugin/agt-core/src/main/java/com/uifuture/maven/plugins/core/model/JavaClassModel.java"));
        JavaClass javaClass1 = builder.getClassByName("JavaClassModel");
        JavaClass javaClass2 = builder.getClassByName("com.uifuture.maven.plugins.core.model.JavaClassModel");
        System.out.println(javaClass1 + "====" + javaClass2);
        JavaClass javaClass3 = builder.getClassByName("JavaProjectBuilder");
        JavaClass javaClass4 = builder.getClassByName("com.thoughtworks.qdox.JavaProjectBuilder");
        System.out.println(javaClass3 + "====" + javaClass4);
    }

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

        getLog().info("开始生成自动化测试代码" +
                "\n" + ConfigConstant.CONFIG_ENTITY
        );

        if (StringUtil.isNotEmpty(skipPackages)) {
            //无法进行预加载的类，设置参数值为null，外部包
            Collections.addAll(BaseConstant.skipPackage, skipPackages.split(";"));
        }

        //获取该包下所有的类
        List<String> javaList = PackageUtil.getClassName(basedir.getPath(), testPackageName, childPackage);
        getLog().info("获取的所有类名为：" + javaList);

        //初始化类源
        initJavaProjectBuilder();

        //class文件绝对路径
        for (String javaName : javaList) {
            getLog().info("当前文件路径：" + javaName);
            if (javaName.endsWith("$1")) {
                //跳过
                getLog().info("跳过:" + javaName);
                continue;
            }
            //文件路径
            GenJava.genTest(javaName);
        }
    }

    /**
     * 初始化类源
     */
    private void initJavaProjectBuilder() {
        //读取包下所有的java类文件
        String mainJava = basedir.getPath() + BaseConstant.JAVA_MAIN_SRC;
        BaseConstant.javaProjectBuilder.addSourceTree(new File(mainJava));
        getLog().info("加载当前模块的类：" + mainJava);

        //加载其他模块的类
        if (StringUtil.isNotEmpty(otherProjectName)) {
            for (String name : otherProjectName.split(";")) {
                String fileName = basedir.getPath().substring(0, basedir.getPath().lastIndexOf("/") + 1) + name + BaseConstant.JAVA_MAIN_SRC;
                BaseConstant.javaProjectBuilder.addSourceTree(new File(fileName));
                getLog().info("加载其他模块的类：" + fileName);
            }
        }

        if (StringUtil.isNotEmpty(mockPackage)) {
            //需要mock的包
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


}