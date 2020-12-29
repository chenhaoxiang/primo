package wiki.primo.generator.mybatis.plus.config.constant;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义常量
 *
 * @author chenhx
 * @since 2020/8/31
 */
public class ConstVal {

    public static List<ConfigConstant> configConstantList = new ArrayList<ConfigConstant>();

    /**
     * 实体包名的KEY 查询配置的key名
     */
    public static final String MODULENAME = "ModuleName";

    public static final String SERIVCE = "Service";
    public static final String SERVICEIMPL = "ServiceImpl";
    public static final String MAPPER = "Mapper";
    public static final String CONTROLLER = "Controller";
    public static final String QUERY = "QueryBo";

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
    public static final String TEMPLATE_SERVICEIMPL = "/template/serviceImpl.java.vm";
    public static final String TEMPLATE_CONTROLLER = "/template/controller.java.vm";
    public static final String QUERY_CONTROLLER = "/template/query.java.vm";
    public static final String TEMPLATE_RESULT_CODE_ENUM = "/template/result_code_enum.java.vm";
    public static final String TEMPLATE_RESULT_MODEL = "/template/result_model.java.vm";
    public static final String TEMPLATE_MYBATIS_PLUS_CONFIG = "/template/config/mybatis_plus_config.java.vm";
    public static final String TEMPLATE_DRUID_CONFIG = "/template/config/druid_config.java.vm";


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

}
