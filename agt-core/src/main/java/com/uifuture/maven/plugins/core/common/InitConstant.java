/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.common;

import com.uifuture.maven.plugins.core.util.StringUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 初始化的值
 * @author chenhx
 * @version InitConstant.java, v 0.1 2019-06-12 11:19 chenhx
 */
public class InitConstant {

    /**
     * 对应类型转换
     */
    public static final Map<String, String> MAPPING = new HashMap<>();

    /**
     * 对应类型的默认值
     */
    public static final Map<String, String> VALUE = new HashMap<>();

    /**
     * 导入的包
     */
    public static final Map<String, String> COLLECTION_VALUE_IMPORT = new HashMap<>();

    /**
     * 导入包需要排除的包名
     */
    public static final Set<String> EXCLUDE_IMPORT_TYPE = new HashSet<>();

    /**
     * 通过全限定名获取简称
     * @param type
     * @return
     */
    public static String getAbbreviation(String type){
        if(StringUtil.isEmpty(type)){
            return type;
        }
        if(type.contains(".")){
            type = type.substring(type.lastIndexOf(".")+1);
        }
        return MAPPING.getOrDefault(type,type);
    }

    static {
        //初始化类型转换
        initMapping();
        //初始化默认值
        initValue();
        //初始化排除的包
        initExclude();
        //初始化集合的默认值 
        initCollectionValue();
    }

    private static void initCollectionValue() {
        COLLECTION_VALUE_IMPORT.put("List", "java.util.ArrayList");
        COLLECTION_VALUE_IMPORT.put("Set", "java.util.HashSet");
        COLLECTION_VALUE_IMPORT.put("Map", "java.util.HashMap");

    }

    private static void initExclude() {
        EXCLUDE_IMPORT_TYPE.add("java.lang.Integer");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Class");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Long");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Double");
        EXCLUDE_IMPORT_TYPE.add("java.lang.String");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Boolean");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Float");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Object");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Short");
        EXCLUDE_IMPORT_TYPE.add("java.lang.StringBuffer");
        EXCLUDE_IMPORT_TYPE.add("java.lang.StringBuilder");
        EXCLUDE_IMPORT_TYPE.add("java.lang.Void");

        EXCLUDE_IMPORT_TYPE.add("Integer");
        EXCLUDE_IMPORT_TYPE.add("Class");
        EXCLUDE_IMPORT_TYPE.add("Long");
        EXCLUDE_IMPORT_TYPE.add("Double");
        EXCLUDE_IMPORT_TYPE.add("String");
        EXCLUDE_IMPORT_TYPE.add("Boolean");
        EXCLUDE_IMPORT_TYPE.add("Byte");
        EXCLUDE_IMPORT_TYPE.add("Float");
        EXCLUDE_IMPORT_TYPE.add("Object");
        EXCLUDE_IMPORT_TYPE.add("Short");
        EXCLUDE_IMPORT_TYPE.add("StringBuffer");
        EXCLUDE_IMPORT_TYPE.add("StringBuilder");

        EXCLUDE_IMPORT_TYPE.add("int");
        EXCLUDE_IMPORT_TYPE.add("long");
        EXCLUDE_IMPORT_TYPE.add("double");
        EXCLUDE_IMPORT_TYPE.add("float");
        EXCLUDE_IMPORT_TYPE.add("boolean");
        EXCLUDE_IMPORT_TYPE.add("char");
        EXCLUDE_IMPORT_TYPE.add("byte");
        EXCLUDE_IMPORT_TYPE.add("short");

        EXCLUDE_IMPORT_TYPE.add("T");
        EXCLUDE_IMPORT_TYPE.add("B");
        EXCLUDE_IMPORT_TYPE.add("M");
        EXCLUDE_IMPORT_TYPE.add("F");
    }

    private static void initMapping() {
        MAPPING.put("java.lang.Integer", "Integer");
        MAPPING.put("java.lang.Class", "Class");
        MAPPING.put("java.lang.Long", "Long");
        MAPPING.put("java.lang.Double", "Double");
        MAPPING.put("java.lang.String", "String");
        MAPPING.put("java.lang.Boolean", "Boolean");
        MAPPING.put("java.lang.Byte", "Byte");
        MAPPING.put("java.lang.Float", "Float");
        MAPPING.put("java.lang.Object", "Object");
        MAPPING.put("java.lang.Short", "Short");
        MAPPING.put("java.lang.StringBuffer", "StringBuffer");
        MAPPING.put("java.lang.StringBuilder", "StringBuilder");
        MAPPING.put("java.lang.Void", "Void");

        MAPPING.put("T", "Object");
        MAPPING.put("B", "Object");
        MAPPING.put("M", "Object");
        MAPPING.put("F", "Object");
    }

    private static void initValue() {
        VALUE.put("String", "\"\"");
        VALUE.put("Integer", "0");
        VALUE.put("Long", "0L");
        VALUE.put("Double", "0.0");
        VALUE.put("Float", "0.0f");
        VALUE.put("Boolean", "true");
        VALUE.put("Class", "null");

        VALUE.put("int", "0");
        VALUE.put("long", "0L");
        VALUE.put("double", "0.0");
        VALUE.put("float", "0.0f");
        VALUE.put("boolean", "true");
        VALUE.put("char", "'0'");
        VALUE.put("byte", "0");
        VALUE.put("short", "0");

        VALUE.put("StringBuffer", "new StringBuffer(\"\")");
        VALUE.put("StringBuilder", "new StringBuilder(\"\")");

        VALUE.put("T", "new Object()");
        VALUE.put("B", "new Object()");
        VALUE.put("M", "new Object()");
        VALUE.put("F", "new Object()");

        VALUE.put("Object", "new Object()");
    }

}