/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.dto;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 类需要导入的包
 * @author chenhx
 * @version JavaImplementsDTO.java, v 0.1 2019-06-10 17:02 chenhx
 */
public class JavaImplementsDTO {
    /**
     * 包的全限定名称
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
        return new StringJoiner(", ", JavaImplementsDTO.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .toString();
    }
}