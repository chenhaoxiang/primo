/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.model;

import java.util.List;
import java.util.StringJoiner;

/**
 * java方法属性
 * @author chenhx
 * @version JavaMethodModel.java, v 0.1 2019-06-11 11:39 chenhx
 */
public class JavaMethodModel {
    /**
     * 方法名称
     */
    private String name;
    /**
     * 方法参数名称
     */
    private List<String> parameterName;

    /**
     * 方法参数类型
     */
    private List<String> parameterType;

    /**
     * 参数数量
     */
    private Integer parameterNum;

    /**
     * 方法返回参数类型
     */
    private String returnType;

    /**
     * Getter method for property <tt>parameterNum</tt>.
     *
     * @return property value of parameterNum
     */
    public Integer getParameterNum() {
        return parameterNum;
    }

    /**
     * Setter method for property <tt>parameterNum</tt>.
     *
     * @param parameterNum value to be assigned to property parameterNum
     */
    public void setParameterNum(Integer parameterNum) {
        this.parameterNum = parameterNum;
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
     * Getter method for property <tt>parameterName</tt>.
     *
     * @return property value of parameterName
     */
    public List<String> getParameterName() {
        return parameterName;
    }

    /**
     * Setter method for property <tt>parameterName</tt>.
     *
     * @param parameterName value to be assigned to property parameterName
     */
    public void setParameterName(List<String> parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * Getter method for property <tt>parameterType</tt>.
     *
     * @return property value of parameterType
     */
    public List<String> getParameterType() {
        return parameterType;
    }

    /**
     * Setter method for property <tt>parameterType</tt>.
     *
     * @param parameterType value to be assigned to property parameterType
     */
    public void setParameterType(List<String> parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * Getter method for property <tt>returnType</tt>.
     *
     * @return property value of returnType
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * Setter method for property <tt>returnType</tt>.
     *
     * @param returnType value to be assigned to property returnType
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JavaMethodModel.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("parameterName=" + parameterName)
                .add("parameterType=" + parameterType)
                .add("parameterNum=" + parameterNum)
                .add("returnType='" + returnType + "'")
                .toString();
    }
}