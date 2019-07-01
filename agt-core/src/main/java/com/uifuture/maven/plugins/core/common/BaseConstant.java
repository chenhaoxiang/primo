/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.common;

import com.thoughtworks.qdox.JavaProjectBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chenhx
 * @version BaseConstant.java, v 0.1 2019-06-11 13:58 chenhx
 */
public class BaseConstant {
    /**
     * 项目文件路径
     */
    public static final String JAVA_MAIN_SRC = "/src/main/java/";
    /**
     * 测试类路径
     */
    public static final String JAVA_TEST_SRC = "/src/test/java/";

    /**
     * 日期
     */
    public static final String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


    /**
     * 需要跳过的包的类，不进行设置默认值,默认值设置为null
     * 第三方类，无法进行加载的类需要该设置
     */
    public static Set<String> skipPackage = new HashSet<>();

    /**
     * 获取类库
     * 预加载类信息
     */
    public static JavaProjectBuilder javaProjectBuilder = new JavaProjectBuilder();

}