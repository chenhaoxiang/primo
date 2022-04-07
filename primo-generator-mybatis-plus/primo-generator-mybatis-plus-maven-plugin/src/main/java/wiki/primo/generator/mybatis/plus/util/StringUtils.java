/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.util;

/**
 * @author chenhx
 * @version 0.0.1
 * @className StringUtils.java
 * @date 2022-04-07 4:08 下午
 * @description 字符串操作
 */
public class StringUtils {

    /**
     * 获取数字
     * @return
     */
    public static Integer getNumber(String input){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c >= '0' && c <= '9'){
                sb.append(c);
            }
        }
        if(StringUtils.isEmpty(sb.toString())){
            return null;
        }
        return Integer.parseInt(sb.toString());
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}
