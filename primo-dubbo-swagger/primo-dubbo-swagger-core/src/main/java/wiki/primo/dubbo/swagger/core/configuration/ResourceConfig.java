package wiki.primo.dubbo.swagger.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * ResourceConfig
 *
 * @author chenhx
 * @date 2019-07-15 17:06
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("dubbo.html")
                .addResourceLocations("classpath:/META-INF/dist/index.html");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/dist/");
    }
}
