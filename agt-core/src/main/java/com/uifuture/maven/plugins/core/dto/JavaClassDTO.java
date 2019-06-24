/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 类DTO
 * @author chenhx
 * @version JavaClassDTO.java, v 0.1 2019-06-10 17:03 chenhx
 */
public class JavaClassDTO {
    /**
     * 类方法
     */
    private List<JavaMethodDTO> javaMethodDTOList;
    /**
     * 导入的包
     */
    private List<JavaImplementsDTO> javaImplementsDTOList;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 包装类的内部属性 - 包含了父类的属性
     */
    private Map<String,List<JavaParameterDTO>> javaParameterDTOMap;

    /**
     * 需要引入的mcok类
     */
    private List<JavaMockClassInfoDTO> javaMockClassInfoDTOList;

    /**
     * Getter method for property <tt>javaMockClassInfoDTOList</tt>.
     *
     * @return property value of javaMockClassInfoDTOList
     */
    public List<JavaMockClassInfoDTO> getJavaMockClassInfoDTOList() {
        return javaMockClassInfoDTOList;
    }

    /**
     * Setter method for property <tt>javaMockClassInfoDTOList</tt>.
     *
     * @param javaMockClassInfoDTOList value to be assigned to property javaMockClassInfoDTOList
     */
    public void setJavaMockClassInfoDTOList(List<JavaMockClassInfoDTO> javaMockClassInfoDTOList) {
        this.javaMockClassInfoDTOList = javaMockClassInfoDTOList;
    }

    /**
     * Getter method for property <tt>javaParameterDTOMap</tt>.
     *
     * @return property value of javaParameterDTOMap
     */
    public Map<String, List<JavaParameterDTO>> getJavaParameterDTOMap() {
        return javaParameterDTOMap;
    }

    /**
     * Setter method for property <tt>javaParameterDTOMap</tt>.
     *
     * @param javaParameterDTOMap value to be assigned to property javaParameterDTOMap
     */
    public void setJavaParameterDTOMap(Map<String, List<JavaParameterDTO>> javaParameterDTOMap) {
        this.javaParameterDTOMap = javaParameterDTOMap;
    }

    /**
     * Getter method for property <tt>javaMethodDTOList</tt>.
     *
     * @return property value of javaMethodDTOList
     */
    public List<JavaMethodDTO> getJavaMethodDTOList() {
        return javaMethodDTOList;
    }

    /**
     * Setter method for property <tt>javaMethodDTOList</tt>.
     *
     * @param javaMethodDTOList value to be assigned to property javaMethodDTOList
     */
    public void setJavaMethodDTOList(List<JavaMethodDTO> javaMethodDTOList) {
        this.javaMethodDTOList = javaMethodDTOList;
    }

    /**
     * Getter method for property <tt>javaImplementsDTOList</tt>.
     *
     * @return property value of javaImplementsDTOList
     */
    public List<JavaImplementsDTO> getJavaImplementsDTOList() {
        return javaImplementsDTOList;
    }

    /**
     * Setter method for property <tt>javaImplementsDTOList</tt>.
     *
     * @param javaImplementsDTOList value to be assigned to property javaImplementsDTOList
     */
    public void setJavaImplementsDTOList(List<JavaImplementsDTO> javaImplementsDTOList) {
        this.javaImplementsDTOList = javaImplementsDTOList;
    }

    /**
     * Getter method for property <tt>packageName</tt>.
     *
     * @return property value of packageName
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Setter method for property <tt>packageName</tt>.
     *
     * @param packageName value to be assigned to property packageName
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JavaClassDTO.class.getSimpleName() + "[", "]")
                .add("javaMethodDTOList=" + javaMethodDTOList)
                .add("javaImplementsDTOList=" + javaImplementsDTOList)
                .add("packageName='" + packageName + "'")
                .add("javaParameterDTOMap=" + javaParameterDTOMap)
                .add("javaMockClassInfoDTOList=" + javaMockClassInfoDTOList)
                .toString();
    }
}