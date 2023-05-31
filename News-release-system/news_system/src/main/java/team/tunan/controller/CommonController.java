package team.tunan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.tunan.dto.LoginParam;
import team.tunan.service.CommonService;
import team.tunan.vo.R;

/**
 * @author Tunan
 * @version 1.0
 */
@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {
    @Autowired
    private CommonService commonService;

    // 权限码请求接口
    @PostMapping("code/request")
    public R getRequestPermissionCode(@RequestBody String emailJson) {
        return commonService.getRequestPermissionCode(emailJson);
    }

    // 邮箱验证码接口
    @PostMapping("code/email")
    public R sendEmailCode(@RequestBody LoginParam loginParam) {
        return commonService.sendEmailCode(loginParam);
    }

}
