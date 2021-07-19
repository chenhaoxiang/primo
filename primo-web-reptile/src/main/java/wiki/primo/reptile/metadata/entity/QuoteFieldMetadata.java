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
 * @description 字段元数据定义 引用类型
 *    该类型的数据，可以继续往下获取数据 例如：列表下获取的url还可以继续获取详情
 *    关联字段，维持 一对多 关系 这个规则获取的值一定要是url地址！！！
 */
@Data
public class QuoteFieldMetadata extends FieldMetadata implements Serializable {
   private static final long serialVersionUID = -2249101568720418477L;

}
