/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.web;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhx
 * @version 0.0.1
 * @className WebInfo.java
 * @date 2021-07-07 4:11 下午
 * @description 第三方网站信息
 */
@Data
public class WebInfo implements Serializable {
   private static final long serialVersionUID = -3869626968876695477L;
    /**
     * 名称
     */
   private String name;
    /**
     * 网站标识
     */
   private String code;

}
