package wiki.primo.dubbo.swagger.core.annotaions;

import wiki.primo.dubbo.swagger.core.configuration.ResourceConfig;
import wiki.primo.dubbo.swagger.core.configuration.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhx
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ResourceConfig.class, SwaggerConfig.class})
public @interface EnableSwaggerMore {
}

