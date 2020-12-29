/*
 * souche.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.enums;

/**
 *
 * @author chenhx
 * @version JsonConfigListScopeEnum.java, v 0.1 2020-05-08 15:52 chenhx
 */
public enum JsonConfigListScopeEnum {
    /**
     * 作用域：全局（global）、包（package）、类（class）、方法（method）
     */
    GLOBAL("全局","global"),
    PACKAGE("包","package"),
    CLASS("类","class"),
    METHOD("方法","method"),
    ;

    private String name;
    private String value;

    JsonConfigListScopeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    public static JsonConfigListScopeEnum getByValue(String value) {
        JsonConfigListScopeEnum[] valueList = JsonConfigListScopeEnum.values();
        for (JsonConfigListScopeEnum v : valueList) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
