/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.builder.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version 0.0.1
 * @className BlogsResp.java
 * @date 2022-04-06 2:39 下午
 * @description
 */
@Data
public class PageFieldBuilder implements Serializable {

   private static final long serialVersionUID = 4105838434101958407L;
    /**
     * Java字段名称
     */
   private String javaName;
    /**
     * Java字段类型
     */
   private String javaType;
    /**
     * 数据库的字段名称
     */
   private String fieldName;
    /**
     * 数据库的字段类型
     */
   private String fieldType;
    /**
     * 数据库的字段描述
     */
   private String fieldDescribe;
    /**
     * 是否是主键，主键不允许编辑
     */
   private Boolean majorKey;
    /**
     * 数据库字符最大长度
     */
   private Integer maxLength;
    /**
     * 是否开启模糊查询
     */
    private Boolean canFuzzy;

}
