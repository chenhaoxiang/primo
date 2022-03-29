/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.config.external.ext;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author chenhx
 * @version 0.0.1
 * @className BuildSwitchConfig.java
 * @date 2022-03-29 5:38 下午
 * @description 构建开关
 */
public class BuildSwitchConfig {
    /**
     * 覆盖生成 DruidConfig 配置开关
     */
    @Parameter(defaultValue = "true")
    private boolean druid;
    /**
     * 覆盖生成 MybatisPlusConfig 配置开关
     */
    @Parameter(defaultValue = "true")
    private boolean mybatisPlusConfig;
    /**
     * 覆盖生成 ResultCodeEnum 配置开关
     */
    @Parameter(defaultValue = "true")
    private boolean resultCodeEnum;
    /**
     * 覆盖生成 ResultModel 配置开关
     */
    @Parameter(defaultValue = "true")
    private boolean resultModel;

    public Boolean getDruid() {
        return druid;
    }

    public Boolean getMybatisPlusConfig() {
        return mybatisPlusConfig;
    }

    public Boolean getResultCodeEnum() {
        return resultCodeEnum;
    }

    public Boolean getResultModel() {
        return resultModel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BuildSwitchConfig{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("                druid=").append(druid);
        sb.append(",                 mybatisPlusConfig=").append(mybatisPlusConfig);
        sb.append(",                 resultCodeEnum=").append(resultCodeEnum);
        sb.append(",                 resultModel=").append(resultModel);
        sb.append('}');
        return sb.toString();
    }
}
