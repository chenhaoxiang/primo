package wiki.primo.dubbo.swagger.core.extension;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;

import java.lang.reflect.Type;

/**
 * ModelExtendsBuilder
 *
 * @author chenhx
 * @date 2019-09-08 22:38
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1001)
public class ModelExtendsBuilder implements ModelBuilderPlugin {

    @Override
    public void apply(ModelContext context) {
        Type type = context.getType();
        context.getBuilder().name(type.getTypeName());
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
