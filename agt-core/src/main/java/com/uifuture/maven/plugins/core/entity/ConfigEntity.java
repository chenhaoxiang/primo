/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.entity;

import lombok.Data;

import java.io.File;

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
    private Boolean isGetChildPackage;

    /**
     * 需要mock掉的包，包下所有类的方法都会被mock
     */
    @Deprecated
    private String mockPackage;
    /**
     * 不需要初始化默认值的类的包名
     * 在参数初始化的时候，进行赋值为null的类，在该包下的类不会进行赋值
     */
    @Deprecated
    private String skipPackages;

    /**
     * 其他的项目名称，一个项目下多个项目模块。项目模块的路径
     */
    @Deprecated
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
     * 配置是否mock掉父类以及自身测试类非测试的方法
     */
    private Boolean isMockThisOtherMethod;

    /**
     * 配置是否设置基础类型的值随机生成
     */
    private Boolean isSetBasicTypesRandomValue;


    /**
     * 配置字符串随机值的位数（例如："10"，表示10位随机字母/数字字符）
     * V1.1.1+
     */
    private String setStringRandomRange;
    /**
     * 配置int/Integer类型随机值的范围（例如："0,1000"，表示[0,1000]范围的int数值，配置固定的值可配置为"0",则int值固定为0）
     * V1.1.1+
     */
    private String setIntRandomRange;
    /**
     * 配置long/Long类型随机值的范围(配置规则与setIntRandomRange类似)
     * V1.1.1+
     */
    private String setLongRandomRange;
    /**
     * 配置boolean/Boolean类型随机值的范围（例如："true,false"，表示true和false随机。配置为"true"/"false"表示为固定的值）
     * V1.1.1+
     */
    private String setBooleanRandomRange;
}