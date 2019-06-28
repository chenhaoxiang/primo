/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.entity;

import lombok.Data;

import java.io.File;
import java.util.StringJoiner;

/**
 * 配置属性
 * @author chenhx
 * @version ConfigEntity.java, v 0.1 2019-06-21 10:49 chenhx
 */
@Data
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

}