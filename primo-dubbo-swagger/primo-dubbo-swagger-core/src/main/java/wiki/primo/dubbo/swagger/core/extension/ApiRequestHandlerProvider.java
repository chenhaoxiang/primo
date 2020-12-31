package wiki.primo.dubbo.swagger.core.extension;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.fastjson.JSON;
import com.fasterxml.classmate.TypeResolver;
import wiki.primo.dubbo.swagger.api.ApiMethod;
import wiki.primo.dubbo.swagger.api.ApiParam;
import wiki.primo.dubbo.swagger.core.common.SwaggerMoreException;
import wiki.primo.dubbo.swagger.core.util.ClassUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.service.RequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;
import wiki.primo.dubbo.swagger.core.util.TypeUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static wiki.primo.dubbo.swagger.core.common.Constant.DEFAULT_COMPLEX_OBJECT_SUFFIX;
import static wiki.primo.dubbo.swagger.core.common.Constant.DEFAULT_PACKAGE_NAME;
import static wiki.primo.dubbo.swagger.core.common.Constant.DOT;
import static wiki.primo.dubbo.swagger.core.common.Constant.GENERATED_PREFIX;
import static wiki.primo.dubbo.swagger.core.common.Constant.UNDERLINE;
import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;
import static springfox.documentation.builders.BuilderDefaults.nullToEmptyList;
import static springfox.documentation.spi.service.contexts.Orderings.byPatternsCondition;

/**
 * ApiRequestHandlerProvider
 *
 * @author chenhx
 * @date 2019-07-14  11:28
 */
@Component
@ApiRequestHandlerProvider.Body
public class ApiRequestHandlerProvider implements RequestHandlerProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestHandlerProvider.class);
    private final List<ServiceBean> serviceBeans;
    private final HandlerMethodResolver methodResolver;
    private final TypeResolver typeResolver;

    @Autowired
    public ApiRequestHandlerProvider(List<ServiceBean> serviceBeans,
                                     HandlerMethodResolver methodResolver,
                                     TypeResolver typeResolver) {
        this.methodResolver = methodResolver;
        this.serviceBeans = serviceBeans;
        this.typeResolver = typeResolver;
    }

    @Override
    public List<RequestHandler> requestHandlers() {
        return byPatternsCondition().sortedCopy(nullToEmptyList(serviceBeans).stream()
                .filter(bean -> nonNull(AnnotationUtils.findAnnotation(bean.getInterfaceClass(), Api.class)))
                .reduce(newArrayList(), toMappingEntries(), (o1, o2) -> o1)
                .stream().map(toRequestHandler()).collect(Collectors.toList()));
    }

    private BiFunction<List<HandlerMethod>, ? super ServiceBean,
            List<HandlerMethod>> toMappingEntries() {
        return (list, bean) -> {
            Object object = AopUtils.isAopProxy(bean.getRef())
                    ? AopProxyUtils.getSingletonTarget(bean.getRef()) : bean.getRef();
            list.addAll(Arrays.stream(bean.getInterfaceClass().getDeclaredMethods())
                    .map(method -> new HandlerMethod(object, method))
                    .collect(Collectors.toList()));
            return list;
        };
    }

    private Function<HandlerMethod, RequestHandler> toRequestHandler() {
        return handlerMethod -> new ApiRequestHandler(methodResolver, handlerMethod,
                allAsRequestBody(handlerMethod)
                        ? annotateBody(handlerMethod)
                        : methodResolver.methodParameters(handlerMethod));
    }

    private List<ResolvedMethodParameter> annotateBody(HandlerMethod handlerMethod) {
        try {
            ResolvedMethodParameter param0;
            List<Class> parameters = newArrayList(handlerMethod.getMethod().getParameterTypes());
            if (parameters.size() == 1) {
                param0 = methodResolver.methodParameters(handlerMethod).get(0);
            } else {
                Class<?> generatedType = mergeIntoGeneratedType(parameters, handlerMethod.getMethod());
                param0 = new ResolvedMethodParameter(generatedType.getSimpleName(),
                        new MethodParameter(handlerMethod.getMethod(), 0),
                        typeResolver.resolve(generatedType));
            }
            return singletonList(param0.annotate(AnnotationUtils.findAnnotation(getClass(), Body.class).body()));
        } catch (Exception e) {
            LOGGER.error("[ApiRequestHandlerProvider->annotateBody]",e);
        }
        return new ArrayList<>(2);
    }

    private Class<?> mergeIntoGeneratedType(List<Class> parameters, Method method) {
        ApiMethod apiMethod = AnnotationUtils.findAnnotation(method, ApiMethod.class);
        if (isNull(apiMethod)) {
            throw new SwaggerMoreException("Method " + method.getDeclaringClass().getName() + "." + method.getName() + "has more than two complex parameters that must be annotated @ApiParams.");
        }
        //构建实体类名
        StringBuilder className = new StringBuilder(DEFAULT_PACKAGE_NAME +
                method.getDeclaringClass().getSimpleName() + DOT +
                method.getName() + DOT +
                GENERATED_PREFIX + method.getName()
        );
        className.append(UNDERLINE).append(parameters.stream().map(Class::getSimpleName).collect(joining("_")));

        List<String> values = newArrayList();

        List<String> names = new ArrayList<>();
        ApiParam[] apiParams = apiMethod.params();
        for (int i = 0; i < apiParams.length; i++) {
            if (StringUtils.isEmpty(apiParams[i].name())) {
                names.add("param" + i);
            } else {
                names.add(apiParams[i].name());
            }
        }


        if (names.size() != apiMethod.params().length) {
            throw new SwaggerMoreException("[ApiRequestHandlerProvider->mergeIntoGeneratedType]Method " + method.getDeclaringClass().getName() + "." + method.getName() + "，The number of parameters does not match the number of parameters in the comment，names=" + JSON.toJSONString(names) + ", names.size()=" + names.size() + ", apiMethod.params().length=" + apiMethod.params().length);
        }

        int i = 0;
        for (ApiParam param : apiMethod.params()) {
//            names.add(param.name());
            values.add(param.value());
            className.append(UNDERLINE).append(names.get(i));
            i++;
        }
        className.append(UNDERLINE).append(DEFAULT_COMPLEX_OBJECT_SUFFIX);
        return ClassUtils.make(className.toString(), method.getParameterTypes(), names, values);
    }

    private boolean allAsRequestBody(HandlerMethod handlerMethod) {
        return Stream.of(handlerMethod.getMethod().getParameters()).anyMatch(p -> TypeUtils.isComplexObjectType(p.getType()));
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Body {
        RequestBody body() default @RequestBody;
    }
}
