/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.dto;

import java.util.List;
import java.util.StringJoiner;

/**
 * 需要mock的类信息
 * @author chenhx
 * @version JavaMockClassInfoDTO.java, v 0.1 2019-06-11 14:35 chenhx
 */
public class JavaMockClassInfoDTO {
    /**
     * 属性名称
     */
    private String name;

    /**
     * 全限定名称，类型
     */
    private String type;

    /**
     * 父类类型
     */
    private String parentType;

    private List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList;

    /**
     * Getter method for property <tt>parentType</tt>.
     *
     * @return property value of parentType
     */
    public String getParentType() {
        return parentType;
    }

    /**
     * Setter method for property <tt>parentType</tt>.
     *
     * @param parentType value to be assigned to property parentType
     */
    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    /**
     * Getter method for property <tt>javaMockMethodInfoDTOList</tt>.
     *
     * @return property value of javaMockMethodInfoDTOList
     */
    public List<JavaMockMethodInfoDTO> getJavaMockMethodInfoDTOList() {
        return javaMockMethodInfoDTOList;
    }

    /**
     * Setter method for property <tt>javaMockMethodInfoDTOList</tt>.
     *
     * @param javaMockMethodInfoDTOList value to be assigned to property javaMockMethodInfoDTOList
     */
    public void setJavaMockMethodInfoDTOList(List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList) {
        this.javaMockMethodInfoDTOList = javaMockMethodInfoDTOList;
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
        return new StringJoiner(", ", JavaMockClassInfoDTO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type='" + type + "'")
                .add("parentType='" + parentType + "'")
                .add("javaMockMethodInfoDTOList=" + javaMockMethodInfoDTOList)
                .toString();
    }
}