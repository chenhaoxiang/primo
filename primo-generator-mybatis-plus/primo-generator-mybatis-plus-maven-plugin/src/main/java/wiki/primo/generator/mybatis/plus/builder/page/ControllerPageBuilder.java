/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.builder.page;

import wiki.primo.generator.mybatis.plus.builder.po.TableInfoPO;

/**
 *
 * 每个页面需要构建的实体类
 * @author chenhx
 * @version 0.0.1
 * @since  2022-03-30 4:57 下午
 */
public class ControllerPageBuilder {
    /**
     * 构建请求的url时需要，请求后端
     */
    private ControllerUrlBuilder controllerUrlBuilder;
    /**
     * 菜单
     */
    private ControllerMenuBuilder controllerMenuBuilder;

    /**
     * 表信息
     */
    private TableInfoPO tableInfoVM;

}
