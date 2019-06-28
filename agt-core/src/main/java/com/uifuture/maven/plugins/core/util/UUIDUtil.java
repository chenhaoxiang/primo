/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.util;

import org.apache.commons.lang3.RandomUtils;

import java.util.UUID;

/**
 * @author chenhx
 * @version UUIDUtil.java, v 0.1 2019-06-10 20:37 chenhx
 */
public class UUIDUtil {


    private static String[] strDigits = {
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"};

    /**
     * 生成10位UUID
     * @return 生成10位UUID
     */
    public static String getID() {
        UUID uuid = UUID.randomUUID();
        // 改变uuid的生成规则
        return HashUtil.convertToHashStr(uuid.getMostSignificantBits(), 5)
                + HashUtil.convertToHashStr(uuid.getLeastSignificantBits(), 5);
    }
    /**
     * 获取随机字符串
     */
    public static String getRandomChar() {
        int rand = RandomUtils.nextInt(0,strDigits.length-1);
        // 改变uuid的生成规则
        return strDigits[rand];
    }

}