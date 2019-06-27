/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 类DTO
 * @author chenhx
 * @version JavaClassDTO.java, v 0.1 2019-06-10 17:03 chenhx
 */
@Data
public class JavaClassDTO {

    /**
     * 被测试的类名
     */
    private String modelNameUpperCamel;

    /**
     * 被测试的类名 - 首字母小写
     */
    private String modelNameLowerCamel;

    /**
     * 当前日期
     */
    private String date;

    /**
     * 作者
     */
    private String author;

    /**
     * 类方法
     */
    private List<JavaMethodDTO> javaMethodDTOList = new ArrayList<>();

    /**
     * 需要导入的包 - 简称相同的类，不会被导入
     */
    private List<JavaImplementsDTO> javaImplementsDTOList = new ArrayList<>();

    /**
     * 被测试类包名
     */
    private String packageName;

    /**
     * 包装类的内部属性 - 包含了父类的属性
     */
    private Map<String,List<JavaParameterDTO>> javaParameterDTOMap = new HashMap<>();

    /**
     * 需要引入的mcok类
     */
    private List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = new ArrayList<>();


}