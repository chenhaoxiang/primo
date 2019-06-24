/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import lombok.Data;

import java.util.List;
import java.util.StringJoiner;

/**
 * 需要mock的方法的信息
 * @author chenhx
 * @version JavaMockClassInfoDTO.java, v 0.1 2019-06-11 14:35 chenhx
 */
@Data
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

}