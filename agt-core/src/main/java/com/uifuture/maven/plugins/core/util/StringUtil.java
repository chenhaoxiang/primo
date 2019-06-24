/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author chenhx
 * @version StringUtil.java, v 0.1 2019-06-10 21:34 chenhx
 */
public class StringUtil extends StringUtils{

    /**
     * 首字母转小写
     * @param name
     * @return 首字母转小写
     */
    public static String strConvertLowerCamel(String name) {
        if(StringUtils.isEmpty(name)){
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toLowerCase();
        return at+name.substring(1);
    }
    /**
     * 首字母转大写
     * @param name
     * @return 首字母转大写
     */
    public static String strConvertUpperCamel(String name) {
        if(StringUtils.isEmpty(name)){
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toUpperCase();
        return at+name.substring(1);
    }

    /**
     * 添加前后分隔符
     * @param configPath
     * @return 返回添加前后路径分隔符的字符串
     */
    public static String addSeparator(String configPath) {
        if(!configPath.startsWith(File.separator)){
            configPath = File.separator + configPath;
        }
        if(!configPath.endsWith(File.separator)){
            configPath = configPath + File.separator;
        }
        return configPath;
    }
}