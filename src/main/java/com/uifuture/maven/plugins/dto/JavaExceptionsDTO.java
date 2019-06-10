/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.dto;

import java.util.StringJoiner;

/**
 * 异常类
 * @author chenhx
 * @version JavaExceptionsDTO.java, v 0.1 2019-06-10 16:57 chenhx
 */
public class JavaExceptionsDTO {
    /**
     * 异常类型
     */
    private String type;

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JavaExceptionsDTO.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .toString();
    }
}