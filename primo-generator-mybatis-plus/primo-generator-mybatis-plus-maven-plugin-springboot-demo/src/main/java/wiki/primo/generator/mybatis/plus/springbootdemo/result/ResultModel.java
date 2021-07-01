package wiki.primo.generator.mybatis.plus.springbootdemo.result;

import wiki.primo.generator.mybatis.plus.springbootdemo.enums.ResultCodeEnum;

import java.io.Serializable;

/**
 * 前后端交互对象
 *
 * @author chenhx
 * @since 2021-07-01
 */
public class ResultModel<T> implements Serializable {
	private static final long serialVersionUID = 8951721595994792067L;
	private Integer code;
	private String message;
	private T data;

	public ResultModel(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultModel(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResultModel(ResultCodeEnum resultCode) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
	}

	public static <T> ResultModel<T> resultModel(Integer code, String message) {
		return new ResultModel<T>(code, message);
	}

	public static <T> ResultModel<T> resultModel(ResultCodeEnum resultCode) {
		return new ResultModel<T>(resultCode);
	}

	public static <T> ResultModel<T> resultModel(Integer code, String message, T t) {
		return new ResultModel<T>(code, message, t);
	}

	/**
     * 处理成功
     *
     * @param t
     * @param <T>
     * @return
     */
	public static <T> ResultModel<T> success(T t) {
		ResultModel<T> resultModel = new ResultModel<>(ResultCodeEnum.SUCCESS);
		resultModel.setData(t);
		return resultModel;
	}
	public static <T> ResultModel<T> successNoData(String message) {
		return new ResultModel<T>(ResultCodeEnum.SUCCESS.getCode(),message);
	}
	public static <T> ResultModel<Object> success() {
		return success("success");
	}

	/**
     * 业务处理失败
     *
     * @param t
     * @param <T>
     * @return
     */
	public static <T> ResultModel<T> fail(T t) {
		ResultModel<T> resultModel = new ResultModel<>(ResultCodeEnum.FAIL);
		resultModel.setData(t);
		return resultModel;
	}
	public static <T> ResultModel<T> failNoData(String message) {
		return new ResultModel<T>(ResultCodeEnum.FAIL.getCode(),message);
	}

	public static <T> ResultModel<T> fail(String message, T date) {
		return new ResultModel<T>(ResultCodeEnum.FAIL.getCode(), message, date);
	}
	public static <T> ResultModel<T> fail() {
		return fail(null);
	}

	/**
     * 服务器内部错误
     *
     * @param t
     * @param <T>
     * @return
     */
	public static <T> ResultModel<T> error(T t) {
		ResultModel<T> resultModel = new ResultModel<>(ResultCodeEnum.INTERNAL_SERVER_ERROR);
		resultModel.setData(t);
		return resultModel;
	}
	public static <T> ResultModel<T> errorNoData(String message) {
		return new ResultModel<T>(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(),message);
	}
	public static <T> ResultModel<T> error() {
		return error(null);
	}


	/**
     * 参数错误
     *
     * @param t
     * @param <T>
     * @return
     */
	public static <T> ResultModel<T> parameterError(T t) {
		ResultModel<T> resultModel = new ResultModel<>(ResultCodeEnum.PARAMETER_ERROR);
		resultModel.setData(t);
		return resultModel;
	}
	public static <T> ResultModel<T> parameterErrorNoData(String message) {
		return new ResultModel<T>(ResultCodeEnum.PARAMETER_ERROR.getCode(),message);
	}
	public static <T> ResultModel<T> parameterError() {
		return parameterError(null);
	}

	/**
     * 认证不通过
     *
     * @param t
     * @param <T>
     * @return
     */
	public static <T> ResultModel<T> unAuthorized(T t) {
		ResultModel<T> resultModel = new ResultModel<>(ResultCodeEnum.UNAUTHORIZED);
		resultModel.setData(t);
		return resultModel;
	}

	public static <T> ResultModel<T> unAuthorized() {
		return unAuthorized(null);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
