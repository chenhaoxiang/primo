/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.metadata.view;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version 0.0.1
 * @className ViewMetadata.java
 * @date 2021-07-07 5:29 下午
 * @description
 */
@Data
public class ViewMetadata implements Serializable {
    private static final long serialVersionUID = 6985294697761220789L;
    /**
     * 网站标识
     */
    private String webCode;
    /**
     * 请求类型 http,无头浏览器
     */
    private String requestType;
    /**
     * 时间规则，这里不能用引用，用秒代替,下一次抓取的间隔时间
     */
    private Integer timeSecond;
}
