package wiki.primo.dubbo.swagger.core.extension;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import springfox.documentation.RequestHandler;
import springfox.documentation.RequestHandlerKey;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static springfox.documentation.spring.web.paths.Paths.splitCamelCase;

/**
 * ApiRequestHandler
 * 扩展来获取Api的方法签名, swagger识别
 *
 * @author chenhx
 * @date 2019-07-14  11:28
 */
@Slf4j
public class ApiRequestHandler implements RequestHandler {

    private final HandlerMethodResolver methodResolver;
    private final HandlerMethod handlerMethod;
    private final List<ResolvedMethodParameter> resolvedMethodParameters;

    public ApiRequestHandler(HandlerMethodResolver methodResolver,
                             HandlerMethod handlerMethod,
                             List<ResolvedMethodParameter> resolvedMethodParameters) {
        this.methodResolver = methodResolver;
        this.handlerMethod = handlerMethod;
        this.resolvedMethodParameters = resolvedMethodParameters;
    }

    @Override
    public Class<?> declaringClass() {
        return handlerMethod.getBeanType();
    }

    @Override
    public boolean isAnnotatedWith(Class<? extends Annotation> annotation) {
        return null != AnnotationUtils.findAnnotation(handlerMethod.getMethod(), annotation);
    }

    @Override
    public PatternsRequestCondition getPatternsCondition() {
        String key = "/" + handlerMethod.getMethod().getDeclaringClass().getSimpleName().replace("Impl", "") + "/" + getName();
        return new PatternsRequestCondition(key);
    }

    @Override
    public String groupName() {
        Class<?> apiClass = handlerMethod.getBeanType();
        return splitCamelCase(apiClass.getInterfaces()[0].getSimpleName(), "-").replace("/", "").toLowerCase();
    }

    @Override
    public String getName() {
        Method method = handlerMethod.getMethod();
        return Stream.of(method.getDeclaringClass().getDeclaredMethods())
                .filter(m -> m.getName().equals(method.getName()))
                .count() == 1
                ? method.getName()
                : method.getName() +
                "_(" + Stream.of(method.getParameters()).map(Parameter::getType)
                .map(Class::getSimpleName).collect(joining(", ")) + ")";
    }

    @Override
    public Set<RequestMethod> supportedMethods() {
        return Sets.newHashSet(RequestMethod.POST);
    }

    @Override
    public Set<? extends MediaType> produces() {
        return Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8, MediaType.TEXT_PLAIN);
    }

    @Override
    public Set<? extends MediaType> consumes() {
        return Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8);
    }

    @Override
    public Set<NameValueExpression<String>> headers() {
        return Sets.newHashSet();
    }

    @Override
    public Set<NameValueExpression<String>> params() {
        return Sets.newHashSet();
    }

    @Override
    public <T extends Annotation> Optional<T> findAnnotation(Class<T> annotation) {
        return Optional.fromNullable(AnnotationUtils.findAnnotation(handlerMethod.getMethod(), annotation));
    }

    @Override
    public RequestHandlerKey key() {
        return new RequestHandlerKey(getPatternsCondition().getPatterns(), supportedMethods(), consumes(), produces());
    }

    @Override
    public List<ResolvedMethodParameter> getParameters() {
        return resolvedMethodParameters;
    }

    @Override
    public ResolvedType getReturnType() {
        return methodResolver.methodReturnType(handlerMethod);
    }

    @Override
    public <T extends Annotation> Optional<T> findControllerAnnotation(Class<T> annotation) {
        return Optional.fromNullable(AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), annotation));
    }

    @Override
    public RequestMappingInfo getRequestMapping() {
        return null;
    }

    @Override
    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    @Override
    public RequestHandler combine(RequestHandler other) {
        return other;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("ApiRequestHandler{");
        sb.append("key=").append(key());
        sb.append('}');
        return sb.toString();
    }
}
