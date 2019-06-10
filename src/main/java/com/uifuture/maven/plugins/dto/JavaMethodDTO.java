/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.dto;

import java.util.List;
import java.util.StringJoiner;

/**
 * 方法
 * @author chenhx
 * @version JavaMethodDTO.java, v 0.1 2019-06-10 16:15 chenhx
 */
public class JavaMethodDTO {
    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法返回类型
     */
    private String methodReturnType;
    /**
     * 方法参数
     */
    private List<JavaParameterDTO> javaParameterDTOList;

    /**
     * 方法异常
     */
    private List<JavaExceptionsDTO> javaExceptionsDTOList;

    /**
     * Getter method for property <tt>javaExceptionsDTOList</tt>.
     *
     * @return property value of javaExceptionsDTOList
     */
    public List<JavaExceptionsDTO> getJavaExceptionsDTOList() {
        return javaExceptionsDTOList;
    }

    /**
     * Setter method for property <tt>javaExceptionsDTOList</tt>.
     *
     * @param javaExceptionsDTOList value to be assigned to property javaExceptionsDTOList
     */
    public void setJavaExceptionsDTOList(List<JavaExceptionsDTO> javaExceptionsDTOList) {
        this.javaExceptionsDTOList = javaExceptionsDTOList;
    }

    /**
     * Getter method for property <tt>javaParameterDTOList</tt>.
     *
     * @return property value of javaParameterDTOList
     */
    public List<JavaParameterDTO> getJavaParameterDTOList() {
        return javaParameterDTOList;
    }

    /**
     * Setter method for property <tt>javaParameterDTOList</tt>.
     *
     * @param javaParameterDTOList value to be assigned to property javaParameterDTOList
     */
    public void setJavaParameterDTOList(List<JavaParameterDTO> javaParameterDTOList) {
        this.javaParameterDTOList = javaParameterDTOList;
    }

    /**
     * Getter method for property <tt>methodName</tt>.
     *
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter method for property <tt>methodName</tt>.
     *
     * @param methodName value to be assigned to property methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter method for property <tt>methodReturnType</tt>.
     *
     * @return property value of methodReturnType
     */
    public String getMethodReturnType() {
        return methodReturnType;
    }

    /**
     * Setter method for property <tt>methodReturnType</tt>.
     *
     * @param methodReturnType value to be assigned to property methodReturnType
     */
    public void setMethodReturnType(String methodReturnType) {
        this.methodReturnType = methodReturnType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JavaMethodDTO.class.getSimpleName() + "[", "]")
                .add("methodName='" + methodName + "'")
                .add("methodReturnType='" + methodReturnType + "'")
                .add("javaParameterDTOList=" + javaParameterDTOList)
                .add("javaExceptionsDTOList=" + javaExceptionsDTOList)
                .toString();
    }
}