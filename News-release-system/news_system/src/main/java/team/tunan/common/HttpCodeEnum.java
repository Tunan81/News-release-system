package team.tunan.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/5/24 20:23
 * @description 常用结果枚举类
 */
@Getter
@AllArgsConstructor
public enum HttpCodeEnum {

    //======================= 其他枚举 ==============================
    /**
     * 没有权限
     */
    PERMISSION_NOT_DEFINED(403, "您没有操作权限"),
    /**
     * 操作成功
     */
    OPERATOR_IS_SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    OPERATOR_IS_FAILED(500, "操作失败"),
    /**
     * 未知异常
     */
    UNKNOWN_ERROR(600, "未知异常");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 返回信息
     */
    private final String message;

}
