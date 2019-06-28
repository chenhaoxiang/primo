/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.base;

import com.uifuture.maven.plugins.core.common.ConfigConstant;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * @author chenhx
 * @version AbstractPlugin.java, v 0.1 2019-06-10 14:15 chenhx
 */
public abstract class AbstractPlugin extends AbstractMojo {

    /**
     * 运行项目的target路径
     */
    @Parameter(property = "target", defaultValue = "${project.build.directory}")
    protected String target;

    /**
     * 运行项目的跟路径
     */
    @Parameter(defaultValue = "${project.basedir}")
    protected File basedir;

    /**
     * 运行项目名
     */
    @Parameter(defaultValue = "${project.name}")
    protected String project;

    /**
     * 配置文件路径
     */
    @Parameter(defaultValue = "/src/main/resources/test/template")
    protected String configPath;

    /**
     * 配置文件名称
     */
    @Parameter(defaultValue = "magt.ftl")
    protected String configFileName;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        ConfigConstant.CONFIG_ENTITY.setBasedir(basedir);
        ConfigConstant.CONFIG_ENTITY.setTarget(target);
        ConfigConstant.CONFIG_ENTITY.setProject(project);
        ConfigConstant.CONFIG_ENTITY.setConfigPath(configPath);
        ConfigConstant.CONFIG_ENTITY.setConfigFileName(configFileName);
    }
}