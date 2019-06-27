/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.common;

import lombok.Data;

/**
 * @author chenhx
 * @version BaseCanUserType.java, v 0.1 2019-06-26 18:39 chenhx
 */
@Data
public class BaseCanUserType {
    /**
     * 参数类型 - 简称
     */
    private String type;

    /**
     * 全限定名称，类型
     */
    private String fullyType;

    /**
     * 是否能够使用简称
     */
    private Boolean canUserType=false;

}