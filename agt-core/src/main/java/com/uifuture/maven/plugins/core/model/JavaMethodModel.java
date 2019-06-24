/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.model;

import lombok.Data;

import java.util.List;
import java.util.StringJoiner;

/**
 * java方法属性
 * @author chenhx
 * @version JavaMethodModel.java, v 0.1 2019-06-11 11:39 chenhx
 */
@Data
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
}