/*
 * wiki.primo
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.config.json;

import lombok.Data;

import java.util.List;

/**
 * 读取json配置类的值
 * @author chenhx
 * @version 1.0.0
 */
@Data
public class JsonConfig {

    /**
     *是否开启json配置-默认false
     */
    private Boolean isOpen;

    /**
     * 值的具体配置
     */
    private List<JsonConfigList> list;

}
