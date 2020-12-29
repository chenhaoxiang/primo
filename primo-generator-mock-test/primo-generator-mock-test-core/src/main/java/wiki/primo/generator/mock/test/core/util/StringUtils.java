/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.File;

/**
 * @author chenhx
 * @version StringUtil.java, v 0.1 2019-06-10 21:34 chenhx
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 随机字符的范围
     * 数字以及大小写字母
     */
    private static char[] strDigits = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 首字母转小写
     *
     * @param name 字符串值，必须字母开头
     * @return 首字母转小写
     */
    public static String strConvertLowerCamel(String name) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(name)) {
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toLowerCase();
        return at + name.substring(1);
    }

    /**
     * 首字母转大写
     *
     * @param name 字符串值，必须字母开头
     * @return 首字母转大写
     */
    public static String strConvertUpperCamel(String name) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(name)) {
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toUpperCase();
        return at + name.substring(1);
    }

    /**
     * 添加前后分隔符
     *
     * @param configPath 路径
     * @return 返回添加前后路径分隔符的字符串
     */
    public static String addSeparator(String configPath) {
//        区分系统
        String os = System.getProperty("os.name");

        if(!os.toLowerCase().contains("windows")){
            //只有在非windows才添加前路径分隔符
            if (!configPath.startsWith(File.separator)) {
                configPath = File.separator + configPath;
            }
        }

        if (!configPath.endsWith(File.separator)) {
            configPath = configPath + File.separator;
        }
        return configPath;
    }

    public static void main( String args[] )
    {
        System.out.println( System.getProperty("os.name") );
        System.out.println( System.getProperty("os.version") );
        System.out.println( System.getProperty("os.arch") );
    }
    /**
     * 获取随机字符
     *
     * @return 返回随机字符
     */
    public static char getRandomChar() {
        int rand = RandomUtils.nextInt(0, strDigits.length - 1);
        // 改变uuid的生成规则
        return strDigits[rand];
    }

    /**
     * 获取随机字符串
     *
     * @param count 字符串长度
     * @return count长度的随机字符串
     */
    public static String getRandomString(int count) {
        return RandomStringUtils.random(count, strDigits);
    }

}
