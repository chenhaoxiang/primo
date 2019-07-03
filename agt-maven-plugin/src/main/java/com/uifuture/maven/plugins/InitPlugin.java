/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins;

import com.uifuture.maven.plugins.base.AbstractPlugin;
import com.uifuture.maven.plugins.core.common.ConfigConstant;
import com.uifuture.maven.plugins.core.util.StringUtil;
import com.uifuture.maven.plugins.core.util.UrlUtil;
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
     * 配置文件下载地址
     */
    private static final String CONFIG_URL = "https://raw.githubusercontent.com/chenhaoxiang/auto-generate-test-maven-plugin/master/doc/template/1.1.3/magt.ftl";
    /**
     * 需要将配置文件下载下来
     * @throws MojoExecutionException
     * @throws MojoFailureException
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //设置配置值
        super.execute();

        getLog().info( "开始下载配置文件" + "\n"+ ConfigConstant.CONFIG_ENTITY
        );

        //通过URL下载文件到本地
        configPath = StringUtil.addSeparator(configPath);
        String path = basedir.getPath() + configPath;
        try {
            UrlUtil.downLoadFromUrl(CONFIG_URL,configFileName,path);
        } catch (Exception e) {
            getLog().error("下载配置文件出现异常",e);
        }
    }

}