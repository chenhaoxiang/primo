package wiki.primo.dubbo.swagger.core.web;

import com.alibaba.fastjson.JSONException;
import wiki.primo.dubbo.swagger.core.common.SwaggerMoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.InvocationTargetException;

import static org.springframework.http.HttpStatus.*;

/**
 * ExceptionMessages
 *
 * @author chenhx
 * @date 2019-07-17 11:37
 */
@Slf4j
@ControllerAdvice(basePackageClasses = ApiInvokeController.class)
@ResponseBody
public class ExceptionMessages {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Object throwable(Throwable e) {
        log.error("内部异常: ", e);
        return "内部异常, 联系Swagger开发人员: " + e.getMessage();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JSONException.class)
    public Object jsonParseException(JSONException e) {
        return "Json解析出错, 检查格式是否正确, 错误信息: " + e.getMessage();
    }

    @ResponseStatus(OK)
    @ExceptionHandler(InvocationTargetException.class)
    public Object invocationTargetException(InvocationTargetException e) {
        Throwable targetException = e.getTargetException();
        log.error("", e);
        return "异常: throw new " + targetException.getClass().getSimpleName()
                + "(\"" + e.getTargetException().getMessage() + "\");";
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NoSuchBeanDefinitionException.class)
    public Object noSuchBeanDefinitionException(NoSuchBeanDefinitionException e) {
        return "找不到Bean对象, name: " + e.getBeanName() + "error: " + e.getMessage();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SwaggerMoreException.class)
    public Object swaggerMoreException(SwaggerMoreException e) {
        return e.getMessage();
    }

}
