package team.tunan.common;

/**
 * 常量类
 */

public interface Constants {

    String CODE_200 = "200"; //成功
    String CODE_401 = "401";  // 权限不足
    String CODE_400 = "400";  // 参数错误
    String CODE_500 = "500"; // 系统错误
    String CODE_600 = "600"; // 其他业务异常
    Object DICT_TYPE_ICON = "icon";

    //key
    String EMAIL = "EMAIL_"; // 邮箱
    String EMAIL_REQUEST_VERIFY = "EMAIL_REQUEST_VERIFY_"; // 邮箱验证码

    String PHONE = "PHONE_"; // 手机号
    String PHONE_REQUEST_VERIFY = "PHONE_REQUEST_VERIFY_"; // 手机验证码

    //缓存时间
    int EXPIRE_TEN_SECOND = 10; // 10s
    int EXPIRE_ONE_MINUTE = 60; // 1分钟
    int EXPIRE_FIVE_MINUTE = 5 * 60; // （五分钟）
    int EXPIRE_HALF_HOUR = 30 * 60; // 半小时（30分钟）
    int EXPIRE_ONE_DAY = 24 * 60 * 60; // （1天）


}
