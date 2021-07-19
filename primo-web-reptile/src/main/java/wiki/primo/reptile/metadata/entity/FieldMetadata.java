/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.metadata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version 0.0.1
 * @className FieldMetadata.java
 * @date 2021-07-07 2:41 下午
 * @description 字段元数据定义
 */
@Data
public class FieldMetadata implements Serializable {
    /**
     * 中文名
     */
    private String name;
    /**
     * code - 代表的列不同，在同一个webCode下唯一
     */
    private String code;
    /**
     * 网站标识
     */
    private String webCode;
    /**
     * 字段类型,one、list、quote
     */
    private String type;
    /**
     * 规则引用 标识
     */
    private String xpathRuleCode;
}
