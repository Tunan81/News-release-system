package team.tunan.common;


import team.tunan.common.HttpCodeEnum;
import lombok.Data;

/**
 * @author: Tunan
 * @createDate: 2023/5/24 20:23
 * @description: 统一结果集处理器 主要用于文件上传接口返回
 * @version： 1.0
 */
@Data
public class ResponseResult<T> {

    /**
     * 请求状态
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 数据信息
     */
    private T data;

    /**
     * 私有构造
     *
     * @param success 请求状态
     * @param code    状态码
     * @param message 状态信息
     * @param data    数据
     * @param <T>     泛型数据
     * @return 结果集处理器
     */
    private static <T> ResponseResult<T> response(Boolean success, Integer code, String message, T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setSuccess(success);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 请求成功返回（一）
     *
     * @param code    状态码
     * @param message 状态信息
     * @param data    数据
     * @param <T>     泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> success(Integer code, String message, T data) {
        return response(true, code, message, data);
    }

    /**
     * 请求成功返回（二）
     *
     * @param message 状态信息
     * @param data    数据
     * @param <T>     泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> success(String message, T data) {
        return response(true, HttpCodeEnum.OPERATOR_IS_SUCCESS.getCode(), message, data);
    }

    /**
     * 请求成功返回（三）
     *
     * @param message 状态信息
     * @param <T>     泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> success(String message) {
        return response(true, HttpCodeEnum.OPERATOR_IS_SUCCESS.getCode(), message, null);
    }

    /**
     * 请求成功返回（四）
     *
     * @param httpCodeEnum 状态枚举
     * @param <T>          泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> success(HttpCodeEnum httpCodeEnum) {
        return response(true, httpCodeEnum.getCode(), httpCodeEnum.getMessage(), null);
    }

    /**
     * 请求成功返回（五）
     *
     * @param httpCodeEnum 状态枚举
     * @param data         数据
     * @param <T>          泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> success(HttpCodeEnum httpCodeEnum, T data) {
        return response(true, httpCodeEnum.getCode(), httpCodeEnum.getMessage(), data);
    }

    /**
     * 请求成功返回（六）
     * @param data 数据
     * @return 结果集处理器
     * @param <T> 泛型数据
     */
    public static <T> ResponseResult<T> success( T data) {
        return response(true, HttpCodeEnum.OPERATOR_IS_SUCCESS.getCode(),HttpCodeEnum.OPERATOR_IS_SUCCESS.getMessage(), data);
    }


    /**
     * 请求失败返回（一）
     *
     * @param code    状态码
     * @param message 状态信息
     * @param data    数据
     * @param <T>     泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> fail(Integer code, String message, T data) {
        return response(false, code, message, data);
    }

    /**
     * 请求失败返回（二）
     *
     * @param message 状态信息
     * @param data    数据
     * @param <T>     泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> fail(String message, T data) {
        return response(false, HttpCodeEnum.OPERATOR_IS_FAILED.getCode(), message, data);
    }

    /**
     * 请求失败返回（三）
     *
     * @param message 状态信息
     * @param <T>     泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> fail(String message) {
        return response(false, HttpCodeEnum.OPERATOR_IS_FAILED.getCode(), message, null);
    }

    /**
     * 请求失败返回（四）
     *
     * @param httpCodeEnum 状态枚举
     * @param <T>          泛型数据
     * @return 结果集处理器
     */
    public static <T> ResponseResult<T> fail(HttpCodeEnum httpCodeEnum) {
        return response(false, httpCodeEnum.getCode(), httpCodeEnum.getMessage(), null);
    }

}
