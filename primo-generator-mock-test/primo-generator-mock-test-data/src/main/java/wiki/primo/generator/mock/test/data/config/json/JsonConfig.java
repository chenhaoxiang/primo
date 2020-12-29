/*
 * souche.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.config.json;

import lombok.Data;

import java.util.List;

/**
 * @author chenhx
 * @version 0.0.1
 * @className JsonConfig.java
 * @date 2020-05-08 15:47
 * @description 读取json配置类的值
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
