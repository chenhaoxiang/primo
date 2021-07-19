/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.model;

import lombok.Data;

/**
 * @author chenhx
 * @version 0.0.1
 * @className PipelineModel.java
 * @date 2021-07-02 5:39 下午
 * @description Pipeline的处理类型
 */
@Data
public class PipelineModel {
    /**
     * 标识
     */
    private String code;
    /**
     * 名称
     */
    private String name;
}
