/*
 * chenhx
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.config.external;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.maven.plugins.annotations.Parameter;
import wiki.primo.generator.mybatis.plus.config.external.ext.BuildSwitchConfig;

/**
 * @author chenhx
 * @version 0.0.1
 */
public class ExtConfig {
    /**
     * 构建的开关
     */
    @Parameter(name = "buildSwitchConfig")
    private BuildSwitchConfig buildSwitchConfig;

    public BuildSwitchConfig getBuildSwitchConfig() {
        return buildSwitchConfig;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExtConfig{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("                buildSwitchConfig=").append(buildSwitchConfig);
        sb.append('}');
        return sb.toString();
    }
}
