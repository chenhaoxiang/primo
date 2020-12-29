/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要mock的方法的信息
 *
 * @author chenhx
 * @version JavaMockClassInfoDTO.java, v 0.1 2019-06-11 14:35 chenhx
 */
@Data
public class JavaMockMethodInfoDTO {
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
    private List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();

    /**
     * 方法返回参数类型 - 全限定名称
     */
    private String returnFullyType;
    /**
     * 方法返回参数类型 名称
     */
    private String returnType;

    /**
     * 是否存在多个 方法，方法名一直且参数数量一致
     */
    private Boolean isHaveMoreMethod;

}
