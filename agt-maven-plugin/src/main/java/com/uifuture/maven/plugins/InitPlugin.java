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
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * 初始化配置文件
 * @author chenhx
 * @version InitPlugin.java, v 0.1 2019-06-10 14:16 chenhx
 */
@Mojo(name="init")
public class InitPlugin extends AbstractPlugin {
    private static Log log = new SystemStreamLog();
    /**
     * 配置文件下载地址
     */
    private static final String CONFIG_URL = "https://raw.githubusercontent.com/chenhaoxiang/auto-generate-test-maven-plugin/master/doc/template/1.1.3/magt.ftl";

    /**
     * 下载文件
     */
    public static void downFile() {
        //通过URL下载文件到本地
        String configPath = StringUtil.addSeparator(ConfigConstant.CONFIG_ENTITY.getConfigPath());
        String path = ConfigConstant.CONFIG_ENTITY.getBasedir().getPath() + configPath;
        try {
            UrlUtil.downLoadFromUrl(CONFIG_URL, ConfigConstant.CONFIG_ENTITY.getConfigFileName(), path);
        } catch (Exception e) {
            log.error("下载配置文件出现异常", e);
        }
    }

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

    }

}