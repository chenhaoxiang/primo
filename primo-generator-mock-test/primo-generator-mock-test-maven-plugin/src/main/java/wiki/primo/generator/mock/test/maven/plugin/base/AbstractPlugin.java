/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.maven.plugin.base;

import wiki.primo.generator.mock.test.core.constant.CommonConstant;
import wiki.primo.generator.mock.test.core.util.StringUtils;
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
     * 运行项目的根路径
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
    @Parameter(defaultValue = "/src/main/resources/test/template/")
    protected String configPath;

    /**
     * 配置文件名称
     */
    @Parameter(defaultValue = "primo-generator-mock-test.ftl")
    protected String configFileName;

    /**
     * json配置文件路径
     */
    @Parameter(defaultValue = "/src/main/resources/test/template/")
    protected String jsonConfigPath;

    /**
     * json配置文件名称
     */
    @Parameter(defaultValue = "primo-generator-mock-test.json")
    protected String jsonConfigFileName;

    /**
     * 是否将Template配置文件下载到本地，默认true
     */
    @Parameter(defaultValue = "true")
    protected Boolean isDownloadTemplateFile;

    /**
     * 是否将json配置文件下载到本地，默认true
     */
    @Parameter(defaultValue = "true")
    protected Boolean isDownloadJsonFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        CommonConstant.CONFIG_ENTITY.setBasedir(basedir);
        CommonConstant.CONFIG_ENTITY.setTarget(target);
        CommonConstant.CONFIG_ENTITY.setProject(project);
        CommonConstant.CONFIG_ENTITY.setConfigPath(StringUtils.addSeparator(configPath));
        CommonConstant.CONFIG_ENTITY.setConfigFileName(configFileName);

        CommonConstant.CONFIG_ENTITY.setJsonConfigPath(StringUtils.addSeparator(jsonConfigPath));
        CommonConstant.CONFIG_ENTITY.setJsonConfigFileName(jsonConfigFileName);
        CommonConstant.CONFIG_ENTITY.setIsDownloadTemplateFile(isDownloadTemplateFile);
        CommonConstant.CONFIG_ENTITY.setIsDownloadJsonFile(isDownloadJsonFile);
    }
}
