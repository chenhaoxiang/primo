package wiki.primo.generator.mybatis.plus.config.rules;

/**
 * 数据库类型定义
 *
 * @author chenhx
 * @since 2020/8/30
 */
public enum DbType {

    MYSQL("myslq"), ORACLE("oracle");

    private String value;

    DbType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
