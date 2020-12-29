/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.model;

import lombok.Data;

import java.util.List;

/**
 * java方法属性
 *
 * @author chenhx
 * @version JavaMethodModel.java, v 0.1 2019-06-11 11:39 chenhx
 */
@Data
public class JavaMethodModel {
    /**
     * 父类类型 - 全限定名
     */
    private String parentClassFullyType;
    /**
     * 调用该方法的属性变量名称
     */
    private String fieldName;
    /**
     * 类的类型 - 全限定类型
     */
    private String classType;

    /**
     * 方法名称
     */
    private String name;
    /**
     * 方法参数
     */
    private List<JavaParameteModel> javaParameteModelList;
    /**
     * 方法返回参数类型 - 全限定 名称
     */
    private String returnFullyType;
    /**
     * 方法返回参数类型 名称
     */
    private String returnType;
}
