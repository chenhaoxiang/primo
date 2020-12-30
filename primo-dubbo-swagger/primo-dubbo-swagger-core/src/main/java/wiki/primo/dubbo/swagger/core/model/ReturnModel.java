/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhx
 * @version ReturnModel.java, v 0.1 2019-11-18 18:02 chenhx
 */
@Data
public class ReturnModel<T> implements Serializable {
    private static final long serialVersionUID = -979878273715912270L;
    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回数据的类型
     */
    private String type;

    /**
     * 实际返回数据
     */
    private T returnData;
}
