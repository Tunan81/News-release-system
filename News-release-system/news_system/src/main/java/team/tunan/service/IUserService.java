package team.tunan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.dto.LoginParam;
import team.tunan.dto.UserDTO;
import team.tunan.entity.User;
import team.tunan.vo.R;

/**
 * @author Tunan
 * @since 2023-03-28
 */
public interface IUserService extends IService<User> {

    Boolean saveUser(User user);//保存用户

    Boolean resetPassword(UserDTO userDTO);//重置密码

    Boolean updateState(UserDTO userDTO);//更新用户状态

    UserDTO login(UserDTO userDTO);//登录

    User register(UserDTO userDTO);//注册

    User getUserInfo(UserDTO userDTO);//获取用户信息

    User getUserInfoById(UserDTO userDTO);//通过手机号获取用户信息

    R findPassword(LoginParam loginParam);//通过邮箱找回密码

    R findPasswordByPhone(LoginParam loginParam);//通过手机找回密码
}
