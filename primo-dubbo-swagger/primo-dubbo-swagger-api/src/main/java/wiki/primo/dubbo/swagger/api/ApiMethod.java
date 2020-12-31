package wiki.primo.dubbo.swagger.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhx
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiMethod {

    String value();

    String notes() default "";

    /**
     * 增加版本说明
     *
     * @return
     */
    String version() default "";

    ApiParam[] params() default {};

    boolean hidden() default false;
}

