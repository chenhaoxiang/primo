/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.api;

import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhx
 * @version ApiModelProperty.java, v 0.1 2019-11-12 18:00 chenhx
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiModelProperty {


    /**
     * 如果是枚举，这里使用枚举的class
     *
     * @return
     */
    Class enumClass() default Object.class;

    /**
     * 如果配置了enumClass，务必配置enumAttributeName
     * 该值对应着allowableValues的值，也就是要填写的枚举的名称
     * 例如，枚举中有name和value属性。需要传的值是value属性。
     * 则该值为"value"字符串
     *
     * @return
     */
    String enumAttributeName() default "value,code";

    /**
     * A brief description of this property.
     */
    String value() default "";

    /**
     * Allows overriding the name of the property.
     *
     * @return the overridden property name
     */
    String name() default "";

    /**
     * Limits the acceptable values for this parameter.
     * <p>
     * There are three ways to describe the allowable values:
     * <ol>
     * <li>To set a list of values, provide a comma-separated list.
     * For example: {@code first, second, third}.</li>
     * <li>To set a range of values, start the value with "range", and surrounding by square
     * brackets include the minimum and maximum values, or round brackets for exclusive minimum and maximum values.
     * For example: {@code range[1, 5]}, {@code range(1, 5)}, {@code range[1, 5)}.</li>
     * <li>To set a minimum/maximum value, use the same format for range but use "infinity"
     * or "-infinity" as the second value. For example, {@code range[1, infinity]} means the
     * minimum allowable value of this parameter is 1.</li>
     * </ol>
     */
    String allowableValues() default "";

    /**
     * Allows for filtering a property from the API documentation. See io.swagger.core.filter.SwaggerSpecFilter.
     */
    String access() default "";

    /**
     * Currently not in use.
     */
    String notes() default "";

    /**
     * The returnData type of the parameter.
     * <p>
     * This can be the class name or a primitive. The value will override the returnData type as read from the class
     * property.
     */
    String dataType() default "";

    /**
     * Specifies if the parameter is required or not.
     */
    boolean required() default false;

    /**
     * Allows explicitly ordering the property in the model.
     */
    int position() default 0;

    /**
     * Allows a model property to be hidden in the Swagger model definition.
     */
    boolean hidden() default false;

    /**
     * A sample value for the property.
     */
    String example() default "";

    /**
     * Allows a model property to be designated as read only.
     *
     * @deprecated As of 1.5.19, replaced by {@link #accessMode()}
     */
    @Deprecated
    boolean readOnly() default false;

    /**
     * Allows to specify the access mode of a model property (AccessMode.READ_ONLY, READ_WRITE)
     *
     * @since 1.5.19
     */
    io.swagger.annotations.ApiModelProperty.AccessMode accessMode() default io.swagger.annotations.ApiModelProperty.AccessMode.AUTO;


    /**
     * Specifies a reference to the corresponding type definition, overrides any other metadata specified
     */

    String reference() default "";

    /**
     * Allows passing an empty value
     *
     * @since 1.5.11
     */
    boolean allowEmptyValue() default false;

    /**
     * @return an optional array of extensions
     */
    Extension[] extensions() default @Extension(properties = @ExtensionProperty(name = "", value = ""));

    enum AccessMode {
        AUTO,
        READ_ONLY,
        READ_WRITE;
    }
}
