/*
 * souche.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.enums;

/**
 *
 * JavaBean类型（custom）/基础类型（base）
 * @author chenhx
 * @version JsonConfigListTypeEnum.java, v 0.1 2020-05-08 15:53 chenhx
 */
public enum JsonConfigListTypeEnum {
    /**
     * JavaBean类型（custom）/基础类型（base）
     */
    CUSTOM("JavaBean类型","custom"),
    BASE("基础类型","base"),
    ;

    private String name;
    private String value;

    JsonConfigListTypeEnum(String name, String value) {
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

    public static JsonConfigListTypeEnum getByValue(String value) {
        JsonConfigListTypeEnum[] valueList = JsonConfigListTypeEnum.values();
        for (JsonConfigListTypeEnum v : valueList) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
