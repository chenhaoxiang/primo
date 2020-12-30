package wiki.primo.dubbo.swagger.core.configuration;

import wiki.primo.dubbo.swagger.core.common.ExtendRequestHandlerSelectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

/**
 * SwaggerConfig
 *
 * @author chenhx
 * @date 2019-07-13 13:56
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "wiki.primo.dubbo.swagger")
public class SwaggerConfig {

    @Bean
    @Autowired
    public Docket complete(ServletContext servletContext) {
        List<ResponseMessage> responseMessageList = newArrayList();
        return new Docket(SWAGGER_2)
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/dubbo";
                    }
                })
                .apiInfo(apiInfo())
                .select()
                .apis(ExtendRequestHandlerSelectors.dubboApi())
                .paths(PathSelectors.any())
                .build()
                .groupName("dubbo")
                .produces(Collections.singleton("application/json"))
                .consumes(Collections.singleton("application/json"))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dubbo接口文档")
                .description("不断尝试，不断进步")
                .version("0.0.1-dev")
                .build();
    }
}
