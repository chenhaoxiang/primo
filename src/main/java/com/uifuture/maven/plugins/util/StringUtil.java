/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chenhx
 * @version StringUtil.java, v 0.1 2019-06-10 21:34 chenhx
 */
public class StringUtil extends StringUtils{

    /**
     * 首字母转小写
     * @param name
     * @return
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
     * @return
     */
    public static String strConvertUpperCamel(String name) {
        if(StringUtils.isEmpty(name)){
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toUpperCase();
        return at+name.substring(1);
    }
}