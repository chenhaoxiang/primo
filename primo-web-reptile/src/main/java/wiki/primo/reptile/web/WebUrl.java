/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.web;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version 0.0.1
 * @className WebListUrl.java
 * @date 2021-07-07 4:13 下午
 * @description
 */
@Data
public class WebUrl implements Serializable {
    private static final long serialVersionUID = 160774725254334174L;

    private String url;

    private String name;
    /**
     * 网站标识
     */
    private String webCode;
    /**
     * 标识
     */
    private String code;
}
