package team.tunan.dto;

import lombok.Getter;

/**
 * @author Tunan
 * @version 1.0
 */
@Getter
public class LoginParam {

    private String email; // 邮箱

    private String password; // 密码

    private String passwordConfirm; // 确认密码

    private String code; // 验证码
}
