/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import com.uifuture.maven.plugins.core.common.BaseCanUserType;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法参数
 *
 * @author chenhx
 * @version JavaParameterDTO.java, v 0.1 2019-06-10 16:43 chenhx
 */
@Data
@ToString(callSuper = true)
public class JavaParameterDTO extends BaseCanUserType {
    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数名称 - 首字母大写
     * 便于setter方法设置
     */
    private String upName;

    /**
     * 是否自定义类型(自定义类型-无法直接赋值基础类型的值)
     */
    private Boolean customType;

    /**
     * 默认值
     */
    private String value;

    /**
     * 如果默认值为NULL
     */
    private List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();

}