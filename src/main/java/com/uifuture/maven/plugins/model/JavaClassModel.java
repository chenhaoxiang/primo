/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.model;

import java.util.List;
import java.util.StringJoiner;

/**
 *
 * 类中方法
 * @author chenhx
 * @version JavaClassModel.java, v 0.1 2019-06-11 11:38 chenhx
 */
public class JavaClassModel {
    /**
     * 属性变量名称
     */
    private String name;
    /**
     * 类型 - 全限定名
     */
    private String type;
    /**
     * 父类类型
     */
    private String parentType;

    /**
     * 类中方法
     */
    private List<JavaMethodModel> javaMethodModelList;


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

    /**
     * Getter method for property <tt>javaMethodModelList</tt>.
     *
     * @return property value of javaMethodModelList
     */
    public List<JavaMethodModel> getJavaMethodModelList() {
        return javaMethodModelList;
    }

    /**
     * Setter method for property <tt>javaMethodModelList</tt>.
     *
     * @param javaMethodModelList value to be assigned to property javaMethodModelList
     */
    public void setJavaMethodModelList(List<JavaMethodModel> javaMethodModelList) {
        this.javaMethodModelList = javaMethodModelList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JavaClassModel.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type='" + type + "'")
                .add("parentType='" + parentType + "'")
                .add("javaMethodModelList=" + javaMethodModelList)
                .toString();
    }
}