/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.builder.page;

import lombok.Data;

import java.io.Serializable;

/**
 * 每个页面的菜单数据
 * @author chenhx
 * @version 0.0.1
 * @since  2022-03-30 4:57 下午
 */
@Data
public class ControllerMenuBuilder implements Serializable {

    private static final long serialVersionUID = 8919318695225823334L;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单url地址 - URL都是需要小写的
     */
    private String url;
    /**
     * TODO 菜单图标
     */
    private String ico;
}
