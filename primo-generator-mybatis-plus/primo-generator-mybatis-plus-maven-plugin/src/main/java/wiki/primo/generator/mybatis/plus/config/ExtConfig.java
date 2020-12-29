/*
 * chenhx
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.config;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author chenhx
 * @version 0.0.1
 */
public class ExtConfig {
    /**
     * 开启 DruidConfig 配置开关
     */
    @Parameter(defaultValue = "false")
    private Boolean openDruid;

    public Boolean getOpenDruid() {
        return openDruid;
    }
}
