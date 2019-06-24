/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.entity;

import java.io.File;
import java.util.StringJoiner;

/**
 * 配置属性
 * @author chenhx
 * @version ConfigEntity.java, v 0.1 2019-06-21 10:49 chenhx
 */
public class ConfigEntity {
    /**
     * 需要测试的项目包名
     */
    private String testPackageName;
    /**
     * 作者
     */
    private String author;
    /**
     * 是否获取子包下的类
     */
    private Boolean childPackage;

    /**
     * 需要mock掉的包，包下所有类的方法都会被mock
     */
    private String mockPackage;
    /**
     * 不需要初始化默认值的类的包名
     * 在参数初始化的时候，进行赋值为null的类，在该包下的类不会进行赋值
     */
    private String skipPackages;

    /**
     * 其他的项目名称，一个项目下多个项目模块。项目模块的路径
     */
    private String otherProjectName;

    /**
     * 运行项目的target路径
     */
    private String target;

    /**
     * 运行项目的跟路径
     */
    private File basedir;

    /**
     * 运行项目名
     */
    private String project;

    /**
     * 配置文件路径
     */
    private String configPath;

    /**
     * 配置文件名称
     */
    private String configFileName;

    /**
     * Getter method for property <tt>testPackageName</tt>.
     *
     * @return property value of testPackageName
     */
    public String getTestPackageName() {
        return testPackageName;
    }

    /**
     * Setter method for property <tt>testPackageName</tt>.
     *
     * @param testPackageName value to be assigned to property testPackageName
     */
    public void setTestPackageName(String testPackageName) {
        this.testPackageName = testPackageName;
    }

    /**
     * Getter method for property <tt>author</tt>.
     *
     * @return property value of author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter method for property <tt>author</tt>.
     *
     * @param author value to be assigned to property author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter method for property <tt>childPackage</tt>.
     *
     * @return property value of childPackage
     */
    public Boolean getChildPackage() {
        return childPackage;
    }

    /**
     * Setter method for property <tt>childPackage</tt>.
     *
     * @param childPackage value to be assigned to property childPackage
     */
    public void setChildPackage(Boolean childPackage) {
        this.childPackage = childPackage;
    }

    /**
     * Getter method for property <tt>mockPackage</tt>.
     *
     * @return property value of mockPackage
     */
    public String getMockPackage() {
        return mockPackage;
    }

    /**
     * Setter method for property <tt>mockPackage</tt>.
     *
     * @param mockPackage value to be assigned to property mockPackage
     */
    public void setMockPackage(String mockPackage) {
        this.mockPackage = mockPackage;
    }

    /**
     * Getter method for property <tt>skipPackages</tt>.
     *
     * @return property value of skipPackages
     */
    public String getSkipPackages() {
        return skipPackages;
    }

    /**
     * Setter method for property <tt>skipPackages</tt>.
     *
     * @param skipPackages value to be assigned to property skipPackages
     */
    public void setSkipPackages(String skipPackages) {
        this.skipPackages = skipPackages;
    }

    /**
     * Getter method for property <tt>otherProjectName</tt>.
     *
     * @return property value of otherProjectName
     */
    public String getOtherProjectName() {
        return otherProjectName;
    }

    /**
     * Setter method for property <tt>otherProjectName</tt>.
     *
     * @param otherProjectName value to be assigned to property otherProjectName
     */
    public void setOtherProjectName(String otherProjectName) {
        this.otherProjectName = otherProjectName;
    }

    /**
     * Getter method for property <tt>target</tt>.
     *
     * @return property value of target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Setter method for property <tt>target</tt>.
     *
     * @param target value to be assigned to property target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Getter method for property <tt>basedir</tt>.
     *
     * @return property value of basedir
     */
    public File getBasedir() {
        return basedir;
    }

    /**
     * Setter method for property <tt>basedir</tt>.
     *
     * @param basedir value to be assigned to property basedir
     */
    public void setBasedir(File basedir) {
        this.basedir = basedir;
    }

    /**
     * Getter method for property <tt>project</tt>.
     *
     * @return property value of project
     */
    public String getProject() {
        return project;
    }

    /**
     * Setter method for property <tt>project</tt>.
     *
     * @param project value to be assigned to property project
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Getter method for property <tt>configPath</tt>.
     *
     * @return property value of configPath
     */
    public String getConfigPath() {
        return configPath;
    }

    /**
     * Setter method for property <tt>configPath</tt>.
     *
     * @param configPath value to be assigned to property configPath
     */
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    /**
     * Getter method for property <tt>configFileName</tt>.
     *
     * @return property value of configFileName
     */
    public String getConfigFileName() {
        return configFileName;
    }

    /**
     * Setter method for property <tt>configFileName</tt>.
     *
     * @param configFileName value to be assigned to property configFileName
     */
    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConfigEntity.class.getSimpleName() + "[", "]")
                .add("testPackageName='" + testPackageName + "'")
                .add("author='" + author + "'")
                .add("childPackage=" + childPackage)
                .add("mockPackage='" + mockPackage + "'")
                .add("skipPackages='" + skipPackages + "'")
                .add("otherProjectName='" + otherProjectName + "'")
                .add("target='" + target + "'")
                .add("basedir=" + basedir)
                .add("project='" + project + "'")
                .add("configPath='" + configPath + "'")
                .add("configFileName='" + configFileName + "'")
                .toString();
    }
}