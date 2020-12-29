/*
 * chenhx
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.config.constant;

import wiki.primo.generator.mybatis.plus.config.PackageConfig;
import wiki.primo.generator.mybatis.plus.config.TemplateConfig;
import wiki.primo.generator.mybatis.plus.util.PackageUtils;

import java.io.File;

/**
 * 一些常量的配置
 * @author chenhx
 * @version 0.0.1
 */
public class ConfigConstant {
    /**
     * vm中的包名
     */
    private String packageInfoKey;
    /**
     * 文件path的key
     */
    private String pathInfoKey;
    /**
     * class文件的生成规则
     */
    private String outputFilesRuleValue;
    /**
     * 包的路径
     */
    private String packageValue;
    /**
     * 模板路径
     */
    private String templatePath;

    /**
     * 独立的覆盖开关
     */
    private Boolean fileOverride = true;


    /**
     * 初始化常量数据
     *
     * @param config 包配置
     * @param template 模板配置
     */
    public static void initConstant(PackageConfig config, TemplateConfig template) {
        ConfigConstant constant = new ConfigConstant("Entity", "entity_path", File.separator + "%s.java", PackageUtils.joinPackage(config.getParent(), config.getEntity()), template.getEntity());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("Mapper", "mapper_path", File.separator + "%sMapper.java", PackageUtils.joinPackage(config.getParent(), config.getMapper()), template.getMapper());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("Xml", "xml_path", File.separator + "%sMapper.xml", PackageUtils.joinPackage(config.getParent(), config.getXml()), template.getXml());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("Service", "serivce_path", File.separator + "I%sService.java", PackageUtils.joinPackage(config.getParent(), config.getService()), template.getService());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("ServiceImpl", "serviceimpl_path", File.separator + "%sServiceImpl.java", PackageUtils.joinPackage(config.getParent(), config.getServiceImpl()), template.getServiceImpl());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("Controller", "controller_path", File.separator + "%sController.java", PackageUtils.joinPackage(config.getParent(), config.getController()), template.getController());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("QueryBo", "query_path", File.separator + "%sQueryBo.java", PackageUtils.joinPackage(config.getParent(), config.getQuery()), template.getQuery());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("ResultCodeEnum", "result_code_enum", File.separator + "ResultCodeEnum.java", PackageUtils.joinPackage(config.getParent(), "enums"), ConstVal.TEMPLATE_RESULT_CODE_ENUM);
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("ResultModel", "result_model", File.separator + "ResultModel.java", PackageUtils.joinPackage(config.getParent(), "result"), ConstVal.TEMPLATE_RESULT_MODEL);
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("MybatisPlusConfig", "mybatis_plus_config", File.separator + "MybatisPlusConfig.java", PackageUtils.joinPackage(config.getParent(), "config"), ConstVal.TEMPLATE_MYBATIS_PLUS_CONFIG);
        ConstVal.configConstantList.add(constant);

        constant = new ConfigConstant("DruidConfig", "mybatis_plus_config", File.separator + "DruidConfig.java", PackageUtils.joinPackage(config.getParent(), "config"), ConstVal.TEMPLATE_DRUID_CONFIG);
        ConstVal.configConstantList.add(constant);
    }


    public ConfigConstant(String packageInfoKey, String pathInfoKey, String outputFilesRuleValue, String packageValue, String templatePath) {
        this.packageInfoKey = packageInfoKey;
        this.pathInfoKey = pathInfoKey;
        this.outputFilesRuleValue = outputFilesRuleValue;
        this.packageValue = packageValue;
        this.templatePath = templatePath;
    }

    public String getPackageInfoKey() {
        return packageInfoKey;
    }

    public String getPathInfoKey() {
        return pathInfoKey;
    }

    public String getOutputFilesRuleValue() {
        return outputFilesRuleValue;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public Boolean getFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(Boolean fileOverride) {
        this.fileOverride = fileOverride;
    }
}
