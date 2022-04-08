package wiki.primo.generator.mybatis.plus.config.external;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * 跟包相关的配置项
 *
 * @author chenhx
 * @since 2020/8/30
 */
public class PackageConfig {

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    @Parameter
    private String parent;

    /**
     * 父包模块名
     */
    @Parameter
    private String moduleName;

    /**
     * Entity包名
     */
    @Parameter(defaultValue = "entity")
    private String entity;

    /**
     * Service包名
     */
    @Parameter(defaultValue = "service")
    private String service;

    /**
     * Service Impl包名
     */
    @Parameter(defaultValue = "service.impl")
    private String serviceImpl;
    /**
     * Service扩展类包名
     */
    @Parameter(defaultValue = "service.ext")
    private String serviceExt;

    /**
     * Service Impl扩展类包名
     */
    @Parameter(defaultValue = "service.ext.impl")
    private String serviceExtImpl;
    /**
     * Mapper包名
     */
    @Parameter(defaultValue = "mapper")
    private String mapper;

    /**
     * Mapper XML包名
     */
    @Parameter(defaultValue = "mapper.xml")
    private String xml;

    /**
     * Controller包名
     */
    @Parameter(defaultValue = "controller")
    private String controller;

    /**
     * query包名
     */
    @Parameter(defaultValue = "query")
    private String query;

    /**
     * entityReq包名
     */
    @Parameter(defaultValue = "domain.req")
    private String entityReq;

    /**
     * entityResp包名
     */
    @Parameter(defaultValue = "domain.resp")
    private String entityResp;
    /**
     * page包名
     */
    @Parameter(defaultValue = "domain.vo.resp")
    private String pageVoResp;
    /**
     * page包名
     */
    @Parameter(defaultValue = "domain.vo.req.page")
    private String pageVoReq;
    /**
     * page包名
     */
    @Parameter(defaultValue = "domain.vo.req")
    private String entityVoReq;

    public String getParent() {
        if (moduleName != null && !"".equals(moduleName.trim())) {
            return parent + "." + moduleName;
        }
        return parent;
    }

    public String getEntityVoReq() {
        if(StringUtils.isEmpty(entityVoReq)){
            return "domain.vo.req";
        }
        return entityVoReq;
    }

    public String getPageVoResp() {
        if(StringUtils.isEmpty(pageVoResp)){
            return "domain.vo.resp";
        }
        return pageVoResp;
    }

    public String getPageVoReq() {
        if(StringUtils.isEmpty(pageVoReq)){
            return "domain.vo.req";
        }
        return pageVoReq;
    }

    public String getEntityReq() {
        if(StringUtils.isEmpty(entityReq)){
            return "domain.req";
        }
        return entityReq;
    }

    public String getEntityResp() {
        if(StringUtils.isEmpty(entityResp)){
            return "domain.resp";
        }
        return entityResp;
    }

    public String getQuery() {
        if(StringUtils.isEmpty(query)){
            return "query";
        }
        return query;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getEntity() {
        return entity;
    }

    public String getService() {
        return service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public String getMapper() {
        return mapper;
    }

    public String getXml() {
        return xml;
    }

    public String getController() {
        if(StringUtils.isEmpty(controller)){
            return "controller";
        }
        return controller;
    }

    public String getServiceExt() {
        if(StringUtils.isEmpty(serviceExt)){
            return "service.ext";
        }
        return serviceExt;
    }

    public String getServiceExtImpl() {
        if(StringUtils.isEmpty(serviceExtImpl)){
            return "service.ext.impl";
        }
        return serviceExtImpl;
    }
}
