/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.builder.page;

import java.util.Set;

/**
 * 构建成Freemarker页面后，表单请求后端时，请求的URL地址和相应的参数构造
 * 每个页面需要构建的实体类
 * @author chenhx
 * @version 0.0.1
 * @since  2022-03-30 4:57 下午
 */
public class ControllerUrlBuilder {
    /**
     * 操作类型
     * 增、删、改、查详情、分页查列表
     */
    private String type;
    /**
     * 请求类型 post/get
     */
    private String requestType;
    /**
     * 请求的url地址
     */
    private String url;

    /**
     * 请求的参数名称,表单中name对应的也是这个值
     */
    private Set<String> params;
}
