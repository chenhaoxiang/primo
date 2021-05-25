/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.test.api.req;

import wiki.primo.dubbo.swagger.api.ApiModelProperty;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author chenhx
 * @version TestReq.java, v 0.1 2019-08-20 09:52 chenhx
 */
public class TestReq implements Serializable {
    private static final long serialVersionUID = -677694316752347202L;
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TestReq{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("                name='").append(name).append('\'');
        sb.append(",                 age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
