package wiki.primo.generator.mybatis.plus.springbootdemo.enums;

/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @author chenhx
 * @since 2022-08-22 21:17:01
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 失败
     */
    FAIL(400, "FAIL"),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    /**
     * 参数错误
     */
    PARAMETER_ERROR(402, "PARAMETER_ERROR"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404, "NOT_FOUND"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    ;

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
