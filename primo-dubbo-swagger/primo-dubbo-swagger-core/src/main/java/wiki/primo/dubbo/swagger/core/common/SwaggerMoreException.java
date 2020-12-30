package wiki.primo.dubbo.swagger.core.common;

import java.io.Serializable;

/**
 * SwaggerMoreException
 *
 * @author chenhx
 * @date 2019-07-17 13:05
 */
public class SwaggerMoreException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -2398171389043021322L;

    public SwaggerMoreException(String message) {
        super(message);
    }
}
