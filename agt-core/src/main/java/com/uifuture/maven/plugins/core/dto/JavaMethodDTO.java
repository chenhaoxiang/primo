/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法
 * @author chenhx
 * @version JavaMethodDTO.java, v 0.1 2019-06-10 16:15 chenhx
 */
@Data
public class JavaMethodDTO {
    /**
     * 是否是私有方法
     */
    private Boolean isPrivate;
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
    private String returnType;
    /**
     * 方法返回类型 - 全限定 名称
     */
    private String returnFullyType;
    /**
     * 方法参数
     */
    private List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();

    /**
     * 方法异常
     */
    private List<JavaExceptionsDTO> javaExceptionsDTOList = new ArrayList<>();

    /**
     * 需要被mock的方法
     */
    private List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList = new ArrayList<>();


}