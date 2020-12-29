/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.model;

import lombok.Data;

/**
 * java参数信息
 *
 * @author chenhx
 * @version JavaParameteModel.java, v 0.1 2019-06-26 17:01 chenhx
 */
@Data
public class JavaParameteModel {
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
     * 参数类型 - 全限定 名称
     */
    private String fullyType;
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

    /**
     * 参数的一个顺序
     */
    private Integer order;
}
