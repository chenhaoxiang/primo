/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins;

import com.uifuture.maven.plugins.base.AbstractPlugin;
import com.uifuture.maven.plugins.util.UrlUtil;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.io.IOException;

/**
 * 初始化配置文件
 * @author chenhx
 * @version InitPlugin.java, v 0.1 2019-06-10 14:16 chenhx
 */
@Mojo(name="init")
public class InitPlugin extends AbstractPlugin {
    /**
     * 配置文件下载地址
     */
    private static final String CONFIG_URL = "";
    /**
     * 需要将配置文件下载下来
     * @throws MojoExecutionException
     * @throws MojoFailureException
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //通过URL下载文件到本地
        if(!configPath.startsWith(File.separator)){
            configPath = File.separator + configPath;
        }
        if(!configPath.endsWith(File.separator)){
            configPath = configPath + File.separator ;
        }
        String path = basedir.getPath() + configPath;
        try {
            UrlUtil.downLoadFromUrl(CONFIG_URL,configFileName,path);
        } catch (Exception e) {
            getLog().error("下载配置文件出现异常",e);
        }

    }
}