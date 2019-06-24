/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import lombok.Data;

import java.util.StringJoiner;

/**
 * 方法参数
 * @author chenhx
 * @version JavaParameterDTO.java, v 0.1 2019-06-10 16:43 chenhx
 */
@Data
public class JavaParameterDTO {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数名称 - 首字母大写
     */
    private String upName;
    /**
     * 参数类型
     */
    private String type;
    /**
     * 是否自定义类型
     */
    private Boolean customType;

    /**
     * 唯一标识
     */
    private String keyName;

    /**
     * 默认值
     */
    private String value;

}