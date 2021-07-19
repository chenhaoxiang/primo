/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.metadata.view;

import lombok.Data;
import wiki.primo.reptile.metadata.entity.FieldMetadata;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhx
 * @version 0.0.1
 * @className ListView.java
 * @date 2021-07-07 5:25 下午
 * @description
 */
@Data
public class OneViewMetadata extends ViewMetadata implements Serializable {
    private static final long serialVersionUID = 7684113208676016589L;
    /**
     * 其中的字段数据
     */
    private List<FieldMetadata> fieldMetadataList;
}
