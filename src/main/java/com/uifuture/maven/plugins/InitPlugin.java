/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins;

import com.uifuture.maven.plugins.base.AbstractPlugin;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * 初始化配置文件
 * @author chenhx
 * @version InitPlugin.java, v 0.1 2019-06-10 14:16 chenhx
 */
@Mojo(name="init")
public class InitPlugin extends AbstractPlugin {

    /**
     * 需要将配置文件下载下来
     * @throws MojoExecutionException
     * @throws MojoFailureException
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

    }
}