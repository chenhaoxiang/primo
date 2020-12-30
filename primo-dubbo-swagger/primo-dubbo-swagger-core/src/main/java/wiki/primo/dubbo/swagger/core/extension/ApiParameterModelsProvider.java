package wiki.primo.dubbo.swagger.core.extension;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import wiki.primo.dubbo.swagger.api.ApiMethod;
import wiki.primo.dubbo.swagger.core.util.TypeUtils;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationModelsProviderPlugin;
import springfox.documentation.spi.service.contexts.OperationModelContextsBuilder;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * ApiOperationModelsProvider
 * 默认只会开启springMVC中带有@RequestBody注解的方法参数, 所以扩展了这个类
 *
 * @author chenhx
 * @date 2019-07-14  14:03
 */
@Component
public class ApiParameterModelsProvider implements OperationModelsProviderPlugin {

    private final TypeResolver typeResolver;

    @Autowired
    public ApiParameterModelsProvider(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Override
    public void apply(RequestMappingContext context) {
        collectApiMethodParams(context);
    }

    private void collectApiMethodParams(RequestMappingContext context) {
        List<ResolvedMethodParameter> parameterTypes = context.getParameters();
        Optional<ApiMethod> optional = context.findAnnotation(ApiMethod.class);
        OperationModelContextsBuilder builder = context.operationModelsBuilder();
        if (optional.isPresent()) {
            parameterTypes.forEach(parameter -> collectAllTypes(context, parameter).forEach(builder::addInputParam));
        }
    }

    private List<ResolvedType> collectAllTypes(RequestMappingContext context, ResolvedMethodParameter parameter) {
        List<ResolvedType> allTypes = newArrayList();
        for (ResolvedType type : collectBindingTypes(context.alternateFor(parameter.getParameterType()), newArrayList())) {
            ApiModel apiModel = AnnotationUtils.getAnnotation(type.getErasedType(), ApiModel.class);
            allTypes.add(type);
            if (apiModel != null) {
                allTypes.addAll(Arrays.stream(apiModel.subTypes())
                        .filter(subType -> subType.getAnnotation(ApiModel.class) != type.getErasedType().getAnnotation(ApiModel.class))
                        .map(typeResolver::resolve).collect(Collectors.toList()));
            }
        }
        return allTypes;
    }

    private List<ResolvedType> collectBindingTypes(ResolvedType type, List<ResolvedType> types) {
        if (TypeUtils.isComplexObjectType(type.getErasedType())) {
            types.add(type);
        }
        if (TypeUtils.isBaseType(type.getErasedType())
                || type.getTypeBindings().isEmpty()) {
            return types;
        }
        for (ResolvedType resolvedType : type.getTypeBindings().getTypeParameters()) {
            collectBindingTypes(resolvedType, types);
        }
        return types;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
