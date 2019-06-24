/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import java.util.StringJoiner;

/**
 * 方法参数
 * @author chenhx
 * @version JavaParameterDTO.java, v 0.1 2019-06-10 16:43 chenhx
 */
public class JavaParameterDTO {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数名称 - 首字母大写
     */
    private String upName;
    /**
     * 参数类型
     */
    private String type;
    /**
     * 是否自定义类型
     */
    private Boolean customType;

    /**
     * 唯一标识
     */
    private String keyName;

    /**
     * 默认值
     */
    private String value;

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter method for property <tt>upName</tt>.
     *
     * @return property value of upName
     */
    public String getUpName() {
        return upName;
    }

    /**
     * Setter method for property <tt>upName</tt>.
     *
     * @param upName value to be assigned to property upName
     */
    public void setUpName(String upName) {
        this.upName = upName;
    }

    /**
     * Setter method for property <tt>value</tt>.
     *
     * @param value value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter method for property <tt>keyName</tt>.
     *
     * @return property value of keyName
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * Setter method for property <tt>keyName</tt>.
     *
     * @param keyName value to be assigned to property keyName
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Getter method for property <tt>customType</tt>.
     *
     * @return property value of customType
     */
    public Boolean getCustomType() {
        return customType;
    }

    /**
     * Setter method for property <tt>customType</tt>.
     *
     * @param customType value to be assigned to property customType
     */
    public void setCustomType(Boolean customType) {
        this.customType = customType;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

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
        return new StringJoiner(", ", JavaParameterDTO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("upName='" + upName + "'")
                .add("type='" + type + "'")
                .add("customType=" + customType)
                .add("keyName='" + keyName + "'")
                .add("value='" + value + "'")
                .toString();
    }
}