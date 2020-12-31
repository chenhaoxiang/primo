package wiki.primo.dubbo.swagger.core.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import wiki.primo.dubbo.swagger.api.ApiMethod;
import wiki.primo.dubbo.swagger.api.ApiParam;
import wiki.primo.dubbo.swagger.core.common.SwaggerMoreException;
import wiki.primo.dubbo.swagger.core.model.ReturnModel;
import wiki.primo.dubbo.swagger.core.util.ClassUtils;
import wiki.primo.dubbo.swagger.core.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;
import static com.alibaba.fastjson.parser.Feature.OrderedField;
import static com.alibaba.fastjson.parser.Feature.SupportAutoType;
import static wiki.primo.dubbo.swagger.core.util.TypeUtils.isComplexObjectType;
import static wiki.primo.dubbo.swagger.core.util.TypeUtils.isContainerType;

/**
 * ApiSwaggerController
 *
 * @author chenhx
 * @date 2019-07-14  23:02
 */
@Slf4j
@Controller
public class ApiInvokeController {

    @PostMapping(value = "/dubbo/{classSimpleName}/{methodName}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ReturnModel<Object> invoke(@PathVariable String classSimpleName,
                              @PathVariable String methodName,
                              HttpServletRequest request) throws IOException, InvocationTargetException, IllegalAccessException {
        Object target = null;
        try {
            target = ApplicationHolder.getBean(classSimpleName.substring(0, 1).toLowerCase() + classSimpleName.substring(1));
        } catch (NoSuchBeanDefinitionException e) {
            //去除第一个i，再转换首字母小写，兼容I开头的dubbo接口
            target = ApplicationHolder.getBean(classSimpleName.substring(1, 2).toLowerCase() + classSimpleName.substring(2));
        }

        Object actualTarget = AopUtils.isAopProxy(target) ? AopProxyUtils.getSingletonTarget(target) : target;
        String invokeId = UUID.randomUUID().toString();
        log.info("[swagger-more] 调用({}): ip: {}, invoke -> {}.{}", invokeId, WebUtils.getRemoteAddr(request), actualTarget.getClass(), methodName);

        //方法名称
        Method method = getMethod(methodName, actualTarget.getClass());
        //获取请求参数的名称
        Enumeration<String> enumeration = request.getParameterNames();

        JSONObject object = new JSONObject();
        ApiMethod apiMethod = AnnotationUtils.findAnnotation(method, ApiMethod.class);

        if (apiMethod == null) {
            //未标注 ApiMethod注解
            log.error("[primo-dubbo-swagger]实现类:{},方法:{}，未标注ApiMethod注解", classSimpleName, methodName);
            throw new SwaggerMoreException("[primo-dubbo-swagger]实现类:" + classSimpleName + ",方法:" + methodName + "，未标注ApiMethod注解 ");
        }

        List<String> names = new ArrayList<>();
        ApiParam[] apiParams = apiMethod.params();
        for (int i = 0; i < apiParams.length; i++) {
            if (StringUtils.isEmpty(apiParams[i].name())) {
                names.add("param" + i);
            } else {
                names.add(apiParams[i].name());
            }
        }

        Map<String, Parameter> parameterMap = getParameterMap(names, method);

        while (enumeration.hasMoreElements()) {
            //将请求参数赋值到方法参数中
            String name = (String) enumeration.nextElement();
            List<Object> values = Stream.of(request.getParameterValues(name))
                    .map(o -> o.startsWith("[") || o.startsWith("{") ? JSONObject.parseObject(o, SupportAutoType, OrderedField) : o).collect(Collectors.toList());
            object.put(name, Objects.isNull(parameterMap.get(name)) || !isContainerType(parameterMap.get(name).getType()) ? values.get(0) : values);
        }
        List<Object> params = Lists.newArrayList();
        String body = getBodyString(request);
        String payload = StringUtils.isEmpty(body) ? object.toJSONString() : body;
        if (method.getParameterTypes().length == 1 && isComplexObjectType(method.getParameterTypes()[0])) {
            params.add(JSONObject.parseObject(payload, method.getParameters()[0].getParameterizedType(), SupportAutoType, OrderedField));
        } else {
            parameterMap.keySet().forEach((name) -> {
                String arg = JSONObject.parseObject(payload, DisableSpecialKeyDetect, OrderedField).getString(name);
                params.add(StringUtils.isEmpty(arg) ? null : arg.startsWith("[") || arg.startsWith("{")
                        ? JSONObject.parseObject(arg, parameterMap.get(name).getParameterizedType(), SupportAutoType, OrderedField)
                        : TypeUtils.cast(arg, parameterMap.get(name).getParameterizedType(), ParserConfig.getGlobalInstance()));
            });
        }
        log.info("[swagger-more] 调用({}): 入参: {}", invokeId, JSONObject.toJSONString(params));
        Object result = method.invoke(target, params.toArray());

        ReturnModel<Object> returnModel = new ReturnModel<>();
        returnModel.setCode("200");
        returnModel.setType(method.getReturnType().getName());

        if (method.getReturnType().equals(void.class)) {
            returnModel.setReturnData("请求成功，方法无返回值");
            return returnModel;
        }

        if (result == null) {
            returnModel.setReturnData(null);
            return returnModel;
        }

        if (ClassUtils.isBaseDataType(method.getReturnType())) {
            //基本类型
            returnModel.setReturnData(result.toString());
            return returnModel;
        }
        returnModel.setReturnData(result);
        return returnModel;
    }

    /**
     * 通过方法名 和 类获取方法
     * TODO 这里获取方法有bug 20200818
     *
     * @param methodName
     * @param clazz
     * @return
     */
    private Method getMethod(String methodName, Class<?> clazz) {
        log.info("[primo-dubbo-swagger]获取的方法名为:{}", methodName);
        List<Method> methodList = new ArrayList<>();
        if (methodName.contains("_(")) {
            String[] methodStr = methodName.split("_");
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                //需要注意是否有同名方法
                if (!method.getName().equals(methodStr[0])) {
                    continue;
                }
                Parameter[] parameters = method.getParameters();
                StringBuilder name = new StringBuilder("(");
                for (int i = 0; i < parameters.length; i++) {
                    String simpleName = parameters[i].getType().getSimpleName();
                    if (i == 0) {
                        name.append(simpleName);
                    } else {
                        name.append(", ").append(simpleName);
                    }
                }
                name.append(")");
                if (name.toString().equals(methodStr[1])) {
                    methodList.add(method);
                }
            }
        } else {
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
                if (declaredMethod.getName().equals(methodName)) {
                    methodList.add(declaredMethod);
                }
            }
        }
        if (methodList.size() == 1) {
            return methodList.get(0);
        }
        if (methodList.size() > 1) {
            //有重名方法 ，需要找
            methodList.removeIf(method -> {
                //只调用public方法
                int mod = method.getModifiers();
                if (!Modifier.isPublic(mod)) {
                    return true;
                }
                //排除没有标注ApiMethod注解的
                ApiMethod apiMethod = AnnotationUtils.findAnnotation(method, ApiMethod.class);
                if (apiMethod == null) {
                    return true;
                }
                return false;
            });
            if (methodList.size() == 1) {
                return methodList.get(0);
            }
        }
        log.error("[primo-dubbo-swagger]在实现类:{},中找不到调用的方法:{}", clazz.getName(), methodName);
        throw new SwaggerMoreException("找不到方法: " + methodName);
    }

    private Map<String, Parameter> getParameterMap(List<String> names, Method method) {
        Map<String, Parameter> map = Maps.newLinkedHashMap();
        if (names.isEmpty()) {
            Stream.of(method.getParameters()).forEach(parameter -> map.put(parameter.getName().replace("arg", "param"), parameter));
            return map;
        }
        for (int i = 0; i < names.size(); i++) {
            map.put(names.get(i), method.getParameters()[i]);
        }
        return map;
    }

    private String getBodyString(HttpServletRequest request) throws IOException {
        String str;
        StringBuilder wholeStr = new StringBuilder();
        while ((str = request.getReader().readLine()) != null) {
            wholeStr.append(str);
        }
        return wholeStr.toString();
    }
}
