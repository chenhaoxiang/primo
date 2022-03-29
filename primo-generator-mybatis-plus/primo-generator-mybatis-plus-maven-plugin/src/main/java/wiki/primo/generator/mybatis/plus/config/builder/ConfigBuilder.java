package wiki.primo.generator.mybatis.plus.config.builder;


import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import wiki.primo.generator.mybatis.plus.config.external.*;
import wiki.primo.generator.mybatis.plus.config.constant.ConfigConstant;
import wiki.primo.generator.mybatis.plus.config.constant.ConstVal;
import wiki.primo.generator.mybatis.plus.config.po.TableFieldVM;
import wiki.primo.generator.mybatis.plus.config.po.TableInfoVM;
import wiki.primo.generator.mybatis.plus.config.rules.DbType;
import wiki.primo.generator.mybatis.plus.config.rules.NamingStrategy;
import wiki.primo.generator.mybatis.plus.config.rules.QuerySQL;
import wiki.primo.generator.mybatis.plus.config.rules.IdStrategy;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 配置汇总 传递给文件生成工具
 *
 * @author chenhx
 * @since 2020/8/30
 */
public class ConfigBuilder {
    private static Log log = new SystemStreamLog();
    /**
     * SQL连接
     */
    private Connection connection;
    /**
     * SQL语句类型
     */
    private QuerySQL querySQL;
    private String superEntityClass;
    private String superMapperClass;
    /**
     * service超类定义
     */
    private String superServiceClass;
    private String superServiceImplClass;
    private String superControllerClass;
    /**
     * ID的字符串类型
     */
    private String idType;
    /**
     * 数据库表信息
     */
    private List<TableInfoVM> tableInfoList;

    /**
     * 包配置详情
     */
    private Map<String, String> packageInfo;
    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;

    /**
     * 模板路径配置信息
     */
    private TemplateConfig template;

    /**
     *
     * 在构造器中处理配置
     *
     * @param packageConfig    包配置
     * @param dataSourceConfig 数据源配置
     * @param strategyConfig   表配置
     * @param outputDir        输出目录
     * @param template        模板配置
     * @param extConfig        扩展的配置
     */
    public ConfigBuilder(PackageConfig packageConfig, DataSourceConfig dataSourceConfig,
                         StrategyConfig strategyConfig,TemplateConfig template,
                         String outputDir,ExtConfig extConfig) {
        //1.初始化表的常量数据
        ConfigConstant.initTableConstant(packageConfig,template);
        //2.初始化一次的文件数据
        ConfigConstant.initOneConstant(packageConfig,extConfig);

        //处理包配置
        handlerPackage(outputDir, packageConfig);
        //数据源配置，解析
        handlerDataSource(dataSourceConfig);
        //处理数据库表 加载数据库表、列、注释相关数据集
        handlerStrategy(strategyConfig);
        this.template = template;
    }

    // ************************ 曝露方法 BEGIN*****************************

    /**
     * 所有包配置信息
     *
     * @return 包配置
     */
    public Map<String, String> getPackageInfo() {
        return packageInfo;
    }

    /**
     * 所有路径配置
     *
     * @return 路径配置
     */
    public Map<String, String> getPathInfo() {
        return pathInfo;
    }

    public String getSuperEntityClass() {
        return superEntityClass;
    }

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    /**
     * 获取超类定义
     *
     * @return 完整超类名称
     */
    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    /**
     * 获取ID类型
     *
     * @return id生成方式
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 表信息
     *
     * @return 所有表信息
     */
    public List<TableInfoVM> getTableInfoList() {
        return tableInfoList;
    }

    /**
     * 模板路径配置信息
     *
     * @return 所以模板路径配置信息
     */
    public TemplateConfig getTemplate() {
        return template == null ? new TemplateConfig() : template;
    }

    // ****************************** 曝露方法 END**********************************

    /**
     * 处理包配置
     *
     * @param config PackageConfig
     */
    private void handlerPackage(String outputDir, PackageConfig config) {
        packageInfo = new HashMap<String, String>();
        //Entity实体包名 查询配置,也就是配置类在项目中的包名。父模块名称
        packageInfo.put(ConstVal.MODULENAME, config.getModuleName());
        //输出路径 查询配置
        pathInfo = new HashMap<String, String>();

        for (List<ConfigConstant> constants : ConstVal.getConfigConstantList()) {
            for (ConfigConstant constant : constants) {
                packageInfo.put(constant.getPackageInfoKey(), constant.getPackageValue());
                pathInfo.put(constant.getPathInfoKey(), joinPath(outputDir, constant.getPackageValue()));
            }
        }
    }

    /**
     * 处理数据源配置
     *
     * @param config DataSourceConfig
     */
    private void handlerDataSource(DataSourceConfig config) {
        connection = config.getConn();
        querySQL = getQuerySQL(config.getDbType());
    }

    /**
     * 处理数据库表 加载数据库表、列、注释相关数据集
     *
     * @param config StrategyConfig
     */
    private void handlerStrategy(StrategyConfig config) {
        //处理superClassName,IdClassType,IdStrategy配置
        processTypes(config);
        //获取所有的数据库表信息
        tableInfoList = getTablesInfo(config);
    }

    /**
     * 处理superClassName,IdClassType,IdStrategy配置
     *
     * @param config 策略配置
     */
    private void processTypes(StrategyConfig config) {
        if (StringUtils.isBlank(config.getSuperServiceClass())) {
            superServiceClass = ConstVal.SUPERD_SERVICE_CLASS;
        } else {
            superServiceClass = config.getSuperServiceClass();
        }
        if (StringUtils.isBlank(config.getSuperServiceImplClass())) {
            superServiceImplClass = ConstVal.SUPERD_SERVICEIMPL_CLASS;
        } else {
            superServiceImplClass = config.getSuperServiceImplClass();
        }
        if (StringUtils.isBlank(config.getSuperMapperClass())) {
            superMapperClass = ConstVal.SUPERD_MAPPER_CLASS;
        } else {
            superMapperClass = config.getSuperMapperClass();
        }
        superEntityClass = config.getSuperEntityClass();
        superControllerClass = config.getSuperControllerClass();

        // ID 策略判断
        if (config.getIdGenType() == IdStrategy.auto) {
            idType = IdStrategy.auto.getValue();
        } else if (config.getIdGenType() == IdStrategy.input) {
            idType = IdStrategy.input.getValue();
        } else if (config.getIdGenType() == IdStrategy.uuid) {
            idType = IdStrategy.uuid.getValue();
        } else {
            idType = IdStrategy.id_worker.getValue();
        }
    }

    /**
     * 处理表对应的类名称
     *
     * @param tableList   表名称
     * @param strategy    命名策略
     * @param tablePrefix
     * @return 补充完整信息后的表
     */
    private List<TableInfoVM> processTable(List<TableInfoVM> tableList, NamingStrategy strategy, String tablePrefix) {
        for (TableInfoVM tableInfo : tableList) {
            tableInfo.setEntityName(NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, tablePrefix)));
            tableInfo.setMapperName(tableInfo.getEntityName() + ConstVal.MAPPER);
            tableInfo.setXmlName(tableInfo.getMapperName());
            tableInfo.setServiceName("I" + tableInfo.getEntityName() + ConstVal.SERIVCE);
            tableInfo.setServiceImplName(tableInfo.getEntityName() + ConstVal.SERVICEIMPL);
            tableInfo.setControllerName(tableInfo.getEntityName() + ConstVal.CONTROLLER);
            //设置查询类名
            tableInfo.setQueryName(tableInfo.getEntityName() + ConstVal.QUERY);
            //扩展类
            tableInfo.setServiceExtName("I" + tableInfo.getEntityName() + ConstVal.SERIVCE_EXT);
            tableInfo.setServiceExtImplName(tableInfo.getEntityName() + ConstVal.SERVICE_EXT_IMPL);
        }
        return tableList;
    }

    /**
     * 获取所有的数据库表信息
     *
     * @return 表信息
     */
    private List<TableInfoVM> getTablesInfo(StrategyConfig config) {
        boolean isInclude = (null != config.getInclude() && config.getInclude().length > 0);
        boolean isExclude = (null != config.getExclude() && config.getExclude().length > 0);
        if (isInclude && isExclude) {
            throw new RuntimeException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        }
        List<TableInfoVM> tableList = new ArrayList<TableInfoVM>();
        Set<String> notExistTables = new HashSet<String>();
        NamingStrategy strategy = config.getNaming();
        NamingStrategy fieldStrategy = config.getFieldNaming();
        PreparedStatement pstate = null;
        try {
            pstate = connection.prepareStatement(querySQL.getTableCommentsSql());
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                String tableName = results.getString(querySQL.getTableName());
                System.out.println("获取到表名:"+tableName);
                if (StringUtils.isNotBlank(tableName)) {
                    String tableComment = results.getString(querySQL.getTableComment());
                    TableInfoVM tableInfo = new TableInfoVM();
                    if (isInclude) {
                        for (String includeTab : config.getInclude()) {
                            if (includeTab.equalsIgnoreCase(tableName)) {
                                tableInfo.setName(tableName);
                                tableInfo.setComment(tableComment);
                            } else {
                                notExistTables.add(includeTab);
                            }
                        }
                    } else if (isExclude) {
                        for (String excludeTab : config.getExclude()) {
                            if (!excludeTab.equalsIgnoreCase(tableName)) {
                                tableInfo.setName(tableName);
                                tableInfo.setComment(tableComment);
                            } else {
                                notExistTables.add(excludeTab);
                            }
                        }
                    } else {
                        tableInfo.setName(tableName);
                        tableInfo.setComment(tableComment);
                    }
                    if (StringUtils.isNotBlank(tableInfo.getName())) {
                        List<TableFieldVM> fieldList = getListFields(tableInfo.getName(), fieldStrategy);
                        tableInfo.setFields(fieldList);
                        tableList.add(tableInfo);
                    }
                } else {
                    System.err.println("当前数据库为空！");
                }
            }
            // 将已经存在的表移除
            for (TableInfoVM tabInfo : tableList) {
                notExistTables.remove(tabInfo.getName());
            }
            if (notExistTables.size() > 0) {
                System.err.println("表 " + notExistTables + " 在数据库中不存在！！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (pstate != null) {
                    pstate.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return processTable(tableList, strategy, config.getTablePrefix());
    }

    /**
     * 将字段信息与表信息关联
     *
     * @param tableName 表名称
     * @param strategy  命名策略
     * @return 表信息
     */
    private List<TableFieldVM> getListFields(String tableName, NamingStrategy strategy) throws SQLException {
        boolean havedId = false;
        //避免关键字
        PreparedStatement pstate = connection.prepareStatement(String.format(querySQL.getTableFieldsSql(), "`"+tableName+"`"));
        ResultSet results = pstate.executeQuery();

        List<TableFieldVM> fieldList = new ArrayList<TableFieldVM>();
        while (results.next()) {
            TableFieldVM field = new TableFieldVM();
            String key = results.getString(querySQL.getFieldKey());
            // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
            boolean isId = StringUtils.isNotBlank(key) && key.toUpperCase().equals("PRI");
            // 处理ID
            if (isId && !havedId) {
                field.setKeyFlag(true);
                havedId = true;
            } else {
                field.setKeyFlag(false);
            }
            // 处理其它信息
            field.setName(results.getString(querySQL.getFieldName()));
            field.setType(results.getString(querySQL.getFieldType()));
            field.setPropertyName(processName(field.getName(), strategy));
            field.setPropertyType(processFiledType(field.getType()));
            field.setComment(results.getString(querySQL.getFieldComment()));
            fieldList.add(field);
        }
        return fieldList;
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isEmpty(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
        return parentDir + packageName;
    }

    /**
     * 处理字段类型
     *
     * @return 转换成JAVA包装类型
     */
    private String processFiledType(String type) {
        if (QuerySQL.MYSQL == querySQL) {
            return processMySqlType(type);
        } else if (QuerySQL.ORACLE == querySQL) {
            return processOracleType(type);
        }
        return null;
    }

    /**
     * 处理字段名称
     *
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy) {
        return processName(name, strategy, null);
    }

    /**
     * 处理字段名称
     *
     * @param name
     * @param strategy
     * @param tablePrefix
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy, String tablePrefix) {
        String propertyName = "";
        if (strategy == NamingStrategy.remove_prefix_and_camel) {
            propertyName = NamingStrategy.removePrefixAndCamel(name, tablePrefix);
        } else if (strategy == NamingStrategy.underline_to_camel) {
            propertyName = NamingStrategy.underlineToCamel(name);
        } else if (strategy == NamingStrategy.remove_prefix) {
            propertyName = NamingStrategy.removePrefix(name, tablePrefix);
        } else {
            propertyName = name;
        }
        return propertyName;
    }

    /**
     * MYSQL字段类型转换
     *
     * @param type 字段类型
     * @return JAVA类型
     */
    private String processMySqlType(String type) {
        String t = type.toLowerCase();
        if (t.contains("char") || t.contains("text")) {
            return "String";
        } else if (t.contains("bigint")) {
            return "Long";
        } else if (t.contains("int")) {
            return "Integer";
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            return "Date";
        } else if (t.contains("bit")) {
            return "Boolean";
        } else if (t.contains("decimal")) {
            return "BigDecimal";
        } else if (t.contains("blob")) {
            return "byte[]";
        } else if (t.contains("float")) {
            return "Float";
        } else if (t.contains("double")) {
            return "Double";
        } else if (t.contains("json") || t.contains("enum")) {
            return "String";
        }
        return "String";
    }

    /**
     * ORACLE字段类型转换
     *
     * @param type 字段类型
     * @return JAVA类型
     */
    private String processOracleType(String type) {
        String t = type.toUpperCase();
        if (t.contains("CHAR")) {
            return "String";
        } else if (t.contains("DATE") || t.contains("TIMESTAMP")) {
            return "Date";
        } else if (t.contains("NUMBER")) {
            if (t.matches("NUMBER\\(+\\d{1}+\\)")) {
                return "Integer";
            } else if (t.matches("NUMBER\\(+\\d{2}+\\)")) {
                return "Long";
            }
            return "Double";
        } else if (t.contains("FLOAT")) {
            return "Float";
        } else if (t.contains("BLOB")) {
            return "Object";
        } else if (t.contains("RAW")) {
            return "byte[]";
        }
        return "String";
    }

    /**
     * 获取当前的SQL类型
     *
     * @return DB类型
     */
    private QuerySQL getQuerySQL(DbType dbType) {
        for (QuerySQL qs : QuerySQL.values()) {
            if (qs.getDbType().equals(dbType.getValue())) {
                return qs;
            }
        }
        return QuerySQL.MYSQL;
    }

}
