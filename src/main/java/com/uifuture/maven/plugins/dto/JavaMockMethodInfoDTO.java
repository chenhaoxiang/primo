/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.dto;

import java.util.List;
import java.util.StringJoiner;

/**
 * 需要mock的方法的信息
 * @author chenhx
 * @version JavaMockClassInfoDTO.java, v 0.1 2019-06-11 14:35 chenhx
 */
public class JavaMockMethodInfoDTO {
    /**
     * 父类类型
     */
    private String parentClassType;
    /**
     * 类的名称
     */
    private String className;
    /**
     * 类的类型
     */
    private String classType;

    /**
     * 方法名称
     */
    private String name;

    /**
     * 参数数量
     */
    private Integer parameterNum;


    /**
     * 方法参数名称
     */
    private List<String> parameterName;

    /**
     * 方法参数类型
     */
    private List<String> parameterType;

    /**
     * 方法返回参数类型
     */
    private String returnType;

    /**
     * Getter method for property <tt>parentClassType</tt>.
     *
     * @return property value of parentClassType
     */
    public String getParentClassType() {
        return parentClassType;
    }

    /**
     * Setter method for property <tt>parentClassType</tt>.
     *
     * @param parentClassType value to be assigned to property parentClassType
     */
    public void setParentClassType(String parentClassType) {
        this.parentClassType = parentClassType;
    }

    /**
     * Getter method for property <tt>className</tt>.
     *
     * @return property value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter method for property <tt>className</tt>.
     *
     * @param className value to be assigned to property className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Getter method for property <tt>classType</tt>.
     *
     * @return property value of classType
     */
    public String getClassType() {
        return classType;
    }

    /**
     * Setter method for property <tt>classType</tt>.
     *
     * @param classType value to be assigned to property classType
     */
    public void setClassType(String classType) {
        this.classType = classType;
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
        return new StringJoiner(", ", JavaMockMethodInfoDTO.class.getSimpleName() + "[", "]")
                .add("parentClassType='" + parentClassType + "'")
                .add("className='" + className + "'")
                .add("classType='" + classType + "'")
                .add("name='" + name + "'")
                .add("parameterNum=" + parameterNum)
                .add("parameterName=" + parameterName)
                .add("parameterType=" + parameterType)
                .add("returnType='" + returnType + "'")
                .toString();
    }
}