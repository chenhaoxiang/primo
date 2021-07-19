/*
 * chenhx
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package wiki.primo.reptile.metadata.view;

import lombok.Data;
import wiki.primo.reptile.metadata.entity.FieldMetadata;
import wiki.primo.reptile.metadata.entity.QuoteFieldMetadata;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhx
 * @version 0.0.1
 * @className ListView.java
 * @date 2021-07-07 5:25 下午
 * @description 对应着获取的数据是一个集合，且在页面上展示的形式是列表的那种多个的
 */
@Data
public class ListViewMetadata extends ViewMetadata implements Serializable {

    private static final long serialVersionUID = 437266403616317444L;
    /**
     * 其中的引用数据
     */
    private List<QuoteFieldMetadata> quoteFieldMetadataList;

}
