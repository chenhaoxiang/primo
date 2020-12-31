package wiki.primo.dubbo.swagger.core.common;

import com.google.common.base.Predicate;
import wiki.primo.dubbo.swagger.core.extension.ApiRequestHandler;
import springfox.documentation.RequestHandler;

/**
 * ExtendRequestHandlerSelectors
 *
 * @author chenhx
 * @date 2019-07-14  11:22
 */
public class ExtendRequestHandlerSelectors {
    public static Predicate<RequestHandler> dubboApi() {
        return input -> input instanceof ApiRequestHandler;
    }
}
