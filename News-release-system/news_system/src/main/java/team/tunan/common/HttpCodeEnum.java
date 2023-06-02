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

    //======================= 通用枚举 ==============================
    EMAIL_ERROR(4001, "邮箱格式不正确"),

    PHONE_ERROR(4001, "手机号格式不正确"),
    PARAM_ERROR(4002, "参数格式不正确"),
    CODE_ERROR(4002, "验证码不正确"),
    PASSWORD_ERROR(4003, "密码错误"),
    USER_NOT_EXIST(4004, "用户不存在"),
    EMAIL_ALREADY_EXIST(4005, "邮箱已被注册"),
    PASSWORD_INCONSISTENT(4006, "密码不一致"),
    PARAM_ILLEGAL(4007, "参数不合法"),

    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    ILLEGAL_OPERATION(88888, "非法操作"),


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
