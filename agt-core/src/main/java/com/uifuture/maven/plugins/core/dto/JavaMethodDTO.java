/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 方法
 * @author chenhx
 * @version JavaMethodDTO.java, v 0.1 2019-06-10 16:15 chenhx
 */
@Data
public class JavaMethodDTO {
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 测试方法名称
     */
    private String methodTestName;

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
     * 被mock的方法信息 - 直接设置为
     */
    private Map<String,List<JavaMockMethodInfoDTO>>  javaMockMethodInfoMap;



}