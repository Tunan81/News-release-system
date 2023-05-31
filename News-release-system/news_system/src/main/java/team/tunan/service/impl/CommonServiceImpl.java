package team.tunan.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import team.tunan.common.Constants;
import team.tunan.common.HttpCodeEnum;
import team.tunan.dto.LoginParam;
import team.tunan.service.CommonService;
import team.tunan.service.ThreadService;
import team.tunan.utils.StringUtil;
import team.tunan.vo.R;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Tunan
 * @version 1.0
 */
@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ThreadService threadService;

    @Override
    public R getRequestPermissionCode(String emailJson) {
        // 非空校验
        if (StringUtils.isBlank(emailJson)) return R.error(HttpCodeEnum.PARAM_ERROR);

        // JSON转换，提取email的值
        String email = JSON.parseObject(emailJson).getString("email").trim();
        // 邮箱校验
        if (!StringUtil.checkEmail(email)) {
            return R.error(HttpCodeEnum.EMAIL_ERROR);
        }

        // 随机生成权限码
        String permissionCode = UUID.randomUUID().toString();

        // 存入redis，缓存10s
        redisTemplate.opsForValue().set(Constants.EMAIL_REQUEST_VERIFY + email, permissionCode, Constants.EXPIRE_TEN_SECOND, TimeUnit.SECONDS);
        return R.ok().data("permissionCode", permissionCode);
    }

    @Override
    public R sendEmailCode(LoginParam loginParam) {
        if (loginParam == null) return R.error(HttpCodeEnum.PARAM_ILLEGAL);

        // 获取权限码和邮箱
        String email = loginParam.getEmail();
        String permissionCode = loginParam.getCode();
        // 参数校验
        if (StringUtils.isAnyBlank(email, permissionCode)) {
            return R.error(HttpCodeEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱校验
            return R.error(HttpCodeEnum.EMAIL_ERROR);
        }else {
            // 权限码比对
            String rightCode = redisTemplate.opsForValue().get(Constants.EMAIL_REQUEST_VERIFY + email);
            if (!permissionCode.equals(rightCode)) {
                // 不通过
                return R.error(HttpCodeEnum.ILLEGAL_OPERATION);
            }
        }

        // 全部通过

        // 随机生成6位数字验证码
        String code = StringUtil.randomSixCode();

        // 正文内容
        String content = "亲爱的用户您好:\n" +
                "您在重工时讯的账号正在进行找回密码操作，此次的验证码为：\n\n" +
                code + "\n\n" +
                "此验证码5分钟内有效，请立即进行下一步操作。 如非你本人操作，请忽略此邮件。\n" +
                "感谢您的使用！";
        // 发送验证码
        threadService.sendSimpleMail(email, "您此次的验证码为：" + code, content);
        // 丢入缓存，设置5分钟过期
        redisTemplate.opsForValue().set(Constants.EMAIL + email, code, Constants.EXPIRE_FIVE_MINUTE, TimeUnit.SECONDS);
        return R.ok();
    }

}
