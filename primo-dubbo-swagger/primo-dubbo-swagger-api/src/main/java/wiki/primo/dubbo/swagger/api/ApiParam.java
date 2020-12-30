/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.api;

import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhx
 * @version ApiParam.java, v 0.1 2019-11-12 16:33 chenhx
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiParam {

    /**
     * 如果是枚举，这里使用枚举的class
     *
     * @return
     */
    Class enumClass() default Object.class;

    /**
     * The parameter name.
     * 非必填
     * <p>
     * The name of the parameter will be derived from the field/method/parameter name,
     * however you can override it.
     * <p>
     * Path parameters must always be named as the path section they represent.
     */
    String name() default "";

    /**
     * A brief description of the parameter.
     */
    String value() default "";

    /**
     * Describes the default value for the parameter.
     * <p>
     * If the parameter is annotated with JAX-RS's {@code @DefaultValue}, that value would
     * be used, but can be overridden by setting this property.
     */
    String defaultValue() default "";

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
     * Specifies if the parameter is required or not.
     * <p>
     * Path parameters will always be set as required, whether you set this property or not.
     */
    boolean required() default false;

    /**
     * Allows for filtering a parameter from the API documentation.
     * <p>
     * See io.swagger.core.filter.SwaggerSpecFilter for further details.
     */
    String access() default "";

    /**
     * Specifies whether the parameter can accept multiple values by having multiple occurrences.
     */
    boolean allowMultiple() default false;

    /**
     * Hides the parameter from the list of parameters.
     */
    boolean hidden() default false;

    /**
     * a single example for non-body type parameters
     *
     * @return
     * @since 1.5.4
     */
    String example() default "";

    /**
     * Examples for the parameter.  Applies only to BodyParameters
     *
     * @return
     * @since 1.5.4
     */
    Example examples() default @Example(value = @ExampleProperty(mediaType = "", value = ""));

    /**
     * Adds the ability to override the detected type
     *
     * @return
     * @since 1.5.11
     */
    String type() default "";

    /**
     * Adds the ability to provide a custom format
     *
     * @return
     * @since 1.5.11
     */
    String format() default "";

    /**
     * Adds the ability to set a format as empty
     *
     * @return
     * @since 1.5.11
     */
    boolean allowEmptyValue() default false;

    /**
     * adds ability to be designated as read only.
     *
     * @since 1.5.11
     */
    boolean readOnly() default false;

    /**
     * adds ability to override collectionFormat with `array` types
     *
     * @since 1.5.11
     */
    String collectionFormat() default "";


}
