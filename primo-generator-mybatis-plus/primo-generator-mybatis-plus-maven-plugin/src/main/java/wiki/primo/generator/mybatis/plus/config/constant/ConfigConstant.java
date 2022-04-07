/*
 * chenhx
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.config.constant;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import wiki.primo.generator.mybatis.plus.config.external.ExtConfig;
import wiki.primo.generator.mybatis.plus.config.external.PackageConfig;
import wiki.primo.generator.mybatis.plus.config.external.TemplateConfig;
import wiki.primo.generator.mybatis.plus.util.PackageUtils;

import java.io.File;

/**
 * 一些常量的配置
 *
 * @author chenhx
 * @version 0.0.1
 */
public class ConfigConstant {
    private static Log log = new SystemStreamLog();
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
     * @param config   包配置
     * @param template 模板配置
     */
    public static void initTableConstant(PackageConfig config, TemplateConfig template) {
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

        //增加扩展类
        constant = new ConfigConstant("ServiceExt", "serivce_ext_path", File.separator + "I%sServiceExt.java", PackageUtils.joinPackage(config.getParent(), config.getServiceExt()), template.getServiceExt());
        constant.setFileOverride(false);
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("ServiceExtImpl", "service_ext_impl_path", File.separator + "%sServiceExtImpl.java", PackageUtils.joinPackage(config.getParent(), config.getServiceExtImpl()), template.getServiceExtImpl());
        constant.setFileOverride(false);
        ConstVal.configConstantList.add(constant);

        constant = new ConfigConstant("EntityReq", "entity_req_path", File.separator + "%sReq.java", PackageUtils.joinPackage(config.getParent(), config.getEntityReq()), template.getEntityReq());
        ConstVal.configConstantList.add(constant);
        constant = new ConfigConstant("EntityResp", "entity_resp_path", File.separator + "%sResp.java", PackageUtils.joinPackage(config.getParent(), config.getEntityResp()), template.getEntityResp());
        ConstVal.configConstantList.add(constant);

        constant = new ConfigConstant("PageVOReq", "page_vo_req", File.separator + "%s"+ConstVal.PAGE_VO_REQ+".java", PackageUtils.joinPackage(config.getParent(), config.getPageVoReq()),  ConstVal.TEMPLATE_PAGE_REQ_ENTITY);
        ConstVal.configConstantList.add(constant);

        constant = new ConfigConstant("EntityVOReq", "entity_vo_req", File.separator + "%s"+ConstVal.ENTITY_VO_REQ+".java", PackageUtils.joinPackage(config.getParent(), config.getEntityVoReq()),  ConstVal.TEMPLATE_ENTITY_VO_REQ_ENTITY);
        ConstVal.configConstantList.add(constant);
    }

    public static void initOneConstant(PackageConfig config, ExtConfig extConfig) {
        log.info("配置的扩展数据："+extConfig);
        //常量,只会有一个类生成的
        ConfigConstant constant = new ConfigConstant("ResultCodeEnum", "result_code_enum", File.separator + "ResultCodeEnum.java", PackageUtils.joinPackage(config.getParent(), "enums"), ConstVal.TEMPLATE_RESULT_CODE_ENUM);
        //是否覆盖写
        constant.setFileOverride(extConfig.getBuildSwitchConfig().getResultCodeEnum());
        ConstVal.oneConfigConstantList.add(constant);
        constant = new ConfigConstant("ResultModel", "result_model", File.separator + "ResultModel.java", PackageUtils.joinPackage(config.getParent(), "result"), ConstVal.TEMPLATE_RESULT_MODEL);
        //是否覆盖写
        constant.setFileOverride(extConfig.getBuildSwitchConfig().getResultModel());
        ConstVal.oneConfigConstantList.add(constant);
        constant = new ConfigConstant("MybatisPlusConfig", "mybatis_plus_config", File.separator + "MybatisPlusConfig.java", PackageUtils.joinPackage(config.getParent(), "config"), ConstVal.TEMPLATE_MYBATIS_PLUS_CONFIG);
        constant.setFileOverride(extConfig.getBuildSwitchConfig().getMybatisPlusConfig());
        ConstVal.oneConfigConstantList.add(constant);
        constant = new ConfigConstant("DruidConfig", "druid_config", File.separator + "DruidConfig.java", PackageUtils.joinPackage(config.getParent(), "config"), ConstVal.TEMPLATE_DRUID_CONFIG);
        constant.setFileOverride(extConfig.getBuildSwitchConfig().getDruid());
        ConstVal.oneConfigConstantList.add(constant);

        constant = new ConfigConstant("PageVOResp", "page_vo_resp", File.separator + "PageVOResp.java", PackageUtils.joinPackage(config.getParent(), config.getPageVoResp()), ConstVal.TEMPLATE_PAGE_RESP_ENTITY);
        constant.setFileOverride(true);
        ConstVal.oneConfigConstantList.add(constant);
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
