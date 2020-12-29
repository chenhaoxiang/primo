/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.model;

import lombok.Data;

import java.util.List;

/**
 * 类信息，作为参数进行流转，单个class生成时，方法参数的传递
 *
 * @author chenhx
 * @version JavaClassModel.java, v 0.1 2019-06-11 11:38 chenhx
 */
@Data
public class JavaClassModel {
    /**
     * 该类的的属性变量名称
     */
    private String name;
    /**
     * 类型 - 全限定名
     */
    private String fullyType;
    /**
     * 类型 - 非全限定名
     */
    private String type;
    /**
     * 父类类型
     */
    private String parentFullyTypeType;

    /**
     * 类中方法
     */
    private List<JavaMethodModel> javaMethodModelList;


}
