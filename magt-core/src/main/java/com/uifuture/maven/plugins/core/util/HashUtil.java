/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.util;

/**
 * @author chenhx
 * @version HashUtil.java, v 0.1 2019-06-10 20:38 chenhx
 */
public class HashUtil {

    private static char hexDigits[] = { '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };

    private static char charDigits[] = {
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

    public static String convertToHashStr(long hid, int len) {
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<len; i++) {
            char c = charDigits[(int) ((hid&0xff) % charDigits.length)];
            sb.append(c);
            hid = hid >> 6;
        }

        return sb.toString();
    }


}