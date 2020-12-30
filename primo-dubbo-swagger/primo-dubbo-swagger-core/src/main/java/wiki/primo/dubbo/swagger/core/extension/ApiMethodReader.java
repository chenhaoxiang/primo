package wiki.primo.dubbo.swagger.core.extension;

import com.google.common.base.Optional;
import wiki.primo.dubbo.swagger.api.ApiMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.DescriptionResolver;

/**
 * ApiOperationProvider
 *
 * @author chenhx
 * @date 2019-07-14  17:02
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1001)
public class ApiMethodReader implements OperationBuilderPlugin {

    private final DescriptionResolver resolver;

    @Autowired
    public ApiMethodReader(DescriptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void apply(OperationContext context) {
        Optional<ApiMethod> optional = context.findAnnotation(ApiMethod.class);
        if (optional.isPresent()) {
            ApiMethod apiMethod = optional.get();
            if (StringUtils.hasText(apiMethod.notes())) {
                context.operationBuilder().notes(resolver.resolve(apiMethod.notes()));
            }
            if (StringUtils.hasText(apiMethod.value())) {
                context.operationBuilder().summary(resolver.resolve(apiMethod.value()));
            }
            if (StringUtils.hasText(apiMethod.version())) {
                //增加版本说明-方法级别的
                context.operationBuilder().summary(resolver.resolve(apiMethod.value() + " - " + apiMethod.version()));
            }

            context.operationBuilder().hidden(apiMethod.hidden());
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
