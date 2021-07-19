/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author chenhx
 * @version 0.0.1
 * @className PipelineUrlModel.java
 * @date 2021-07-02 5:43 下午
 * @description 列表
 */
@Data
public class PipelineListModel extends PipelineModel{
    /**
     * key - name
     * value - 值
     */
    private Map<String,Object> urls;
}
