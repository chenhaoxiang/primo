/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.common;

import com.uifuture.maven.plugins.model.JavaClassModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenhx
 * @version BaseConstant.java, v 0.1 2019-06-11 13:58 chenhx
 */
public class BaseConstant {

    public static final String JAVA_MAIN_SRC = "/src/main/java/";
    public static final String JAVA_TEST_SRC = "/src/test/java/";

    /**
     * 需要mock的java类，全限定名称
     */
    public static Set<String> mockJavaSet = new HashSet<>();

    /**
     * 需要mock的类的信息 父类的信息
     */
    public static Map<String, JavaClassModel> mockParentJavaClassModelMap = new HashMap<>();

    /**
     * 需要导入的包，string-类简称，value-全称限定类名的，如果有多个，后面的使用全限定名
     */
    public static Map<String, String> implementsJavaPackageMap = new HashMap<>();
}