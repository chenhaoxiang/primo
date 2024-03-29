package wiki.primo.generator.mybatis.plus.builder.po;

/**
 * Developer:chenhx
 * Date:2020-9-3
 * Describe: 字段信息
 */
public class TableFieldPO {
    /**
     * 是否是主键id
     */
    private boolean keyFlag;

    /**
     * 表的数据库列名
     */
    private String name;
    /**
     * 数据库类型
     */
    private String type;
    /**
     * java属性变量名称
     */
    private String propertyName;
    /**
     * Java类型
     */
    private String propertyType;

    /**
     * 描述
     */
    private String comment;

    /**
     * 全部大写的表列名
     */
    private String nameUp;

    /**
     * Getter method for property <tt>nameUp</tt>.
     *
     * @return property value of nameUp
     */
    public String getNameUp() {
        return name.toUpperCase();
    }

    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isConvert() {
        return !name.equals(propertyName);
    }

    public String getCapitalName() {
        return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableFieldPO{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("                keyFlag=").append(keyFlag);
        sb.append(",                 name='").append(name).append('\'');
        sb.append(",                 type='").append(type).append('\'');
        sb.append(",                 propertyName='").append(propertyName).append('\'');
        sb.append(",                 propertyType='").append(propertyType).append('\'');
        sb.append(",                 comment='").append(comment).append('\'');
        sb.append(",                 nameUp='").append(nameUp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
