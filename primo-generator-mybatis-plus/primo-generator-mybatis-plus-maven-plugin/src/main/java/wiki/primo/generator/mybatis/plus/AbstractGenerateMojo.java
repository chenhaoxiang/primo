package wiki.primo.generator.mybatis.plus;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Parameter;
import wiki.primo.generator.mybatis.plus.builder.ConfigBuilder;
import wiki.primo.generator.mybatis.plus.config.external.*;

/**
 * 插件基类，用于属性配置 设计成抽象类主要是用于后期可扩展，共享参数配置。
 *
 * @author chenhx
 * @since 2020/8/30
 */
public abstract class AbstractGenerateMojo extends AbstractMojo {

    /**
     * 日志工具
     */
    protected Log log = getLog();
    /**
     * 数据源配置
     */
    @Parameter(required = true)
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    @Parameter
    private StrategyConfig strategy;
    /**
     * 扩展配置
     */
    @Parameter
    private ExtConfig extConfig;
    /**
     * 包 相关配置
     */
    @Parameter
    private PackageConfig packageInfo;
    /**
     * 模板 相关配置
     */
    @Parameter
    private TemplateConfig template;
    /**
     * 生成文件的输出目录
     */
    @Parameter
    private String outputDir;
    /**
     * 项目模块下 resource的目录，默认是 src/main/resources/
     */
    @Parameter(defaultValue = "src/main/resources/")
    private String outputResourcesDir;
    /**
     * 是否覆盖已有文件 - 全局的覆盖，这个开启覆盖后，独立的覆盖才会生效
     */
    @Parameter(defaultValue = "false")
    private boolean fileOverride;
    /**
     * 是否打开输出目录
     */
    @Parameter(defaultValue = "true")
    private boolean open;
    /**
     * 是否在xml中添加二级缓存配置
     */
    @Parameter(defaultValue = "true")
    private boolean enableCache;
    /**
     * 开发人员
     */
    @Parameter(defaultValue = "author")
    private String author;
    /**
     * 开启 ActiveRecord 模式
     */
    @Parameter(defaultValue = "true")
    private boolean activeRecord;

    /**
     * ftl文件的一些配置
     */
    @Parameter
    private FtlConfig ftlConfig;

    /**
     * 初始化配置
     * @return 构建好的ConfigBuilder数据
     */
    protected ConfigBuilder initConfig() {
        return new ConfigBuilder(packageInfo, dataSource, strategy, template, outputDir,extConfig);
    }

    public String getOutputResourcesDir() {
        return outputResourcesDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public boolean isActiveRecord() {
        return activeRecord;
    }

    public ExtConfig getExtConfig() {
        return extConfig;
    }

    public FtlConfig getFtlConfig() {
        return ftlConfig;
    }
}
