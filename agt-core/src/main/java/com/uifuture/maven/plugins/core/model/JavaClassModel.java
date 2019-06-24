/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.model;

import lombok.Data;

import java.util.List;
import java.util.StringJoiner;

/**
 *
 * 类中方法
 * @author chenhx
 * @version JavaClassModel.java, v 0.1 2019-06-11 11:38 chenhx
 */
@Data
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
}