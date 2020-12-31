package wiki.primo.dubbo.swagger.core.extension;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import wiki.primo.dubbo.swagger.api.ApiMethod;
import wiki.primo.dubbo.swagger.api.ApiParam;
import wiki.primo.dubbo.swagger.core.common.SwaggerMoreException;
import wiki.primo.dubbo.swagger.core.util.EnumUtils;
import wiki.primo.dubbo.swagger.core.util.StringUtils;
import wiki.primo.dubbo.swagger.core.util.TypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.Example;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.spring.web.DescriptionResolver;

import java.util.List;

import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.collect.Lists.newArrayList;
import static wiki.primo.dubbo.swagger.core.common.Constant.DEFAULT_COMPLEX_OBJECT_SUFFIX;
import static wiki.primo.dubbo.swagger.core.common.Constant.GENERATED_PREFIX;
import static springfox.documentation.swagger.common.SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER;
import static springfox.documentation.swagger.readers.parameter.Examples.examples;

/**
 * ApiParamReader
 *
 * @author chenhx
 * @date 2019-09-06 21:59
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1001)
public class ApiParamReader implements ParameterBuilderPlugin {

    private final DescriptionResolver resolver;

    @Autowired
    public ApiParamReader(DescriptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void apply(ParameterContext context) {
        ResolvedType resolvedType = context.resolvedMethodParameter().getParameterType();
        Class erasedType = resolvedType.getErasedType();
        if (isGeneratedType(erasedType)) {
            context.parameterBuilder().name(erasedType.getSimpleName())
                    .description("Not a real parameter, it is a parameter generated after assembly.");
            return;
        }
        Optional<ApiParam> optional = readApiParam(context);
        if (optional.isPresent()) {
            ApiParam apiParam = optional.get();
            List<VendorExtension> extensions = newArrayList();
            extensions.add(new StringVendorExtension("className", resolvedType.toString()));
            StringBuilder desc = new StringBuilder(emptyToNull(resolver.resolve(apiParam.value())));
            desc.append(EnumUtils.getEnumClassDesc(apiParam.enumClass()));

            String name = apiParam.name();
            if (StringUtils.isEmpty(name)) {
                ResolvedMethodParameter resolvedMethodParameter = context.resolvedMethodParameter();
                if (resolvedMethodParameter.defaultName().isPresent()) {
                    name = resolvedMethodParameter.defaultName().get();
                }
            }

//            context.parameterBuilder().name(emptyToNull(apiParam.name()))
            context.parameterBuilder().name(emptyToNull(name))
                    //描述
                    .description(desc.toString())
                    .parameterType(TypeUtils.isComplexObjectType(erasedType) ? "body" : "query")
                    .order(SWAGGER_PLUGIN_ORDER)
                    .hidden(false)
                    .parameterAccess(emptyToNull(apiParam.access()))
                    .defaultValue(emptyToNull(apiParam.defaultValue()))
                    .allowMultiple(apiParam.allowMultiple())
                    .allowEmptyValue(apiParam.allowEmptyValue())
                    .required(apiParam.required())
                    .scalarExample(new Example(apiParam.example()))
                    .complexExamples(examples(apiParam.examples()))
                    .collectionFormat(apiParam.collectionFormat())
                    .vendorExtensions(extensions);
        }
    }

    private boolean isGeneratedType(Class type) {
        return type.getSimpleName().startsWith(GENERATED_PREFIX) && type.getSimpleName().endsWith(DEFAULT_COMPLEX_OBJECT_SUFFIX);
    }

    private Optional<ApiParam> readApiParam(ParameterContext context) {
        //方法
        Optional<ApiMethod> optional = context.getOperationContext().findAnnotation(ApiMethod.class);
        if (optional.isPresent()) {
            //返回一个不为空的ApiMethod实例
            ApiMethod apiMethod = optional.get();
            ResolvedMethodParameter parameter = context.resolvedMethodParameter();
            try {
                if (parameter.getParameterIndex() >= apiMethod.params().length) {
                    throw new SwaggerMoreException("The number of parameters in method " + context.getOperationContext().getName() + " does not match the number of @ApiParam.");
                }
                return Optional.of(apiMethod.params()[parameter.getParameterIndex()]);

            } catch (Exception e) {
                log.error("[ApiParamReader->readApiParam]error," + "The number of parameters in method " + context.getOperationContext().getName() + " does not match the number of @ApiParam.apiMethod.params().length=" + apiMethod.params().length + ",parameter.getParameterIndex()=" + parameter.getParameterIndex(), e);
            }
        }
        return Optional.absent();
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
