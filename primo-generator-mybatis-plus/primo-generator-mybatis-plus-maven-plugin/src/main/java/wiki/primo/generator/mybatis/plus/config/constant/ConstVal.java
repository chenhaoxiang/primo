package wiki.primo.generator.mybatis.plus.config.constant;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 定义常量
 *
 * @author chenhx
 * @since 2020/8/31
 */
public class ConstVal {
    /**
     * 表循环需要生成的文件
     */
    public static List<ConfigConstant> configConstantList = new ArrayList<ConfigConstant>();
    /**
     * 只需要生成一次的文件数据
     */
    public static List<ConfigConstant> oneConfigConstantList = new ArrayList<ConfigConstant>();

    /**
     * 表循环需要生成的文件
     * ftl的文件
     */
//    public static List<ConfigConstant> configFtlConstantList = new ArrayList<ConfigConstant>();

    /**
     * 包含表中循环生成的文件，以及单个生成一次的文件
     * 注意，生成文件获取需要在这里新增一行
     * @return 返回list，如果有新增的，在这里要添加进去。
     */
    public static List<List<ConfigConstant>> getConfigConstantList(){
        List<List<ConfigConstant>> conList = new ArrayList<>(2);
//        conList.add(configFtlConstantList);
        conList.add(configConstantList);
        conList.add(oneConfigConstantList);
        return conList;
    }

    /**
     * 不进行覆盖的文件名，包含包名
     * 目前只是扩展类的类名
     */
    public static Set<String> extSet = new HashSet<>();

    /**
     * 实体包名的KEY 查询配置的key名
     */
    public static final String MODULENAME = "ModuleName";

    public static final String SERIVCE = "Service";
    public static final String SERVICEIMPL = "ServiceImpl";
    public static final String SERIVCE_EXT = "ServiceExt";
    public static final String SERVICE_EXT_IMPL = "ServiceExtImpl";
    public static final String MAPPER = "Mapper";
    public static final String CONTROLLER = "Controller";
    public static final String QUERY = "QueryBo";
    public static final String PAGE_VO_REQ = "PageVOReq";
    public static final String ENTITY_VO_REQ = "EntityVOReq";
    public static final String FTL_TABLE = "table";

    /**
     * Java输入输出临时路径
     */
    public static final String JAVA_TMPDIR = "java.io.tmpdir";
    public static final String UTF8 = Charset.forName("UTF-8").name();
    public static final String UNDERLINE = "_";


    public static final String TEMPLATE_ENTITY = "/template/entity.java.vm";
    public static final String TEMPLATE_MAPPER = "/template/mapper.java.vm";
    public static final String TEMPLATE_XML = "/template/mapper.xml.vm";
    public static final String TEMPLATE_SERVICE = "/template/service.java.vm";
    public static final String TEMPLATE_SERVICEIMPL = "/template/service_impl.java.vm";
    public static final String TEMPLATE_CONTROLLER = "/template/controller.java.vm";
    public static final String QUERY_CONTROLLER = "/template/query.java.vm";
    public static final String TEMPLATE_RESULT_CODE_ENUM = "/template/result_code_enum.java.vm";
    public static final String TEMPLATE_RESULT_MODEL = "/template/result_model.java.vm";
    public static final String TEMPLATE_MYBATIS_PLUS_CONFIG = "/template/config/mybatis_plus_config.java.vm";
    public static final String TEMPLATE_DRUID_CONFIG = "/template/config/druid_config.java.vm";
    public static final String TEMPLATE_REQ_ENTITY = "/template/domain/entity-req.java.vm";
    public static final String TEMPLATE_RESP_ENTITY = "/template/domain/entity-resp.java.vm";
    /**
     * 扩展类
     */
    public static final String TEMPLATE_SERVICE_EXT = "/template/service_ext.java.vm";
    public static final String TEMPLATE_SERVICE_EXT_IMPL = "/template/service_ext_impl.java.vm";


    /**
     * 配置使用classloader加载资源
     */
    public static final String VM_LOADPATH_KEY = "file.resource.loader.class";
    public static final String VM_LOADPATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    /**
     * mapper接口默认实现的接口
     */
    public static final String SUPERD_MAPPER_CLASS = "com.baomidou.mybatisplus.core.mapper.BaseMapper";
    /**
     * service接口默认实现接口
     */
    public static final String SUPERD_SERVICE_CLASS = "com.baomidou.mybatisplus.extension.service.IService";
    /**
     * service实现类默认继承的类
     */
    public static final String SUPERD_SERVICEIMPL_CLASS = "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl";

//    page
    public static final String TEMPLATE_PAGE_REQ_ENTITY = "/template/domain/vo/req/page/page_vo_req.java.vm";
    public static final String TEMPLATE_ENTITY_VO_REQ_ENTITY = "/template/domain/vo/req/entity_vo_req.java.vm";
    /**
     * 只生成一次
     */
    public static final String TEMPLATE_PAGE_RESP_ENTITY = "/template/domain/vo/resp/page_vo_resp.java.vm";
    /**
     * 表格页面
     */
    public static final String TEMPLATE_PAGE_TABLE = "/template/page/table.ftl.vm";

}
