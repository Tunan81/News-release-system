package team.tunan.service;

import team.tunan.dto.LoginParam;
import team.tunan.vo.R;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/5/30 20:59
 * @description 公共服务
 */
public interface CommonService {

    /**
     * 获取请求权限码
     * @param emailJson 邮箱
     * @return 权限码
     */
    R getRequestPermissionCode(String emailJson);

    /**
     * 发送邮箱验证码
     * @param loginParam （邮箱和权限码）
     * @return 是否发送成功
     */
    R sendEmailCode(LoginParam loginParam);

}
