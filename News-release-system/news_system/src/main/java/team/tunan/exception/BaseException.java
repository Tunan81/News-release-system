package team.tunan.exception;

import team.tunan.common.HttpCodeEnum;
import lombok.Getter;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/05/24 20:59
 * @description 基础异常处理类
 */
@Getter
public class BaseException extends RuntimeException {

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 报错信息
     */
    private final String message;

    /**
     * 全参构造方法
     *
     * @param code    状态码
     * @param message 报错信息
     */
    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param message 报错信息
     */
    public BaseException(String message) {
        this(HttpCodeEnum.OPERATOR_IS_FAILED.getCode(), message);
    }

    /**
     * 构造方法
     * @param httpCodeEnum http枚举类
     */
    public BaseException(HttpCodeEnum httpCodeEnum) {
        this(httpCodeEnum.getCode(), httpCodeEnum.getMessage());
    }

}
