package team.tunan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import team.tunan.common.Constants;
import team.tunan.common.Result;
import team.tunan.dto.UserDTO;
import team.tunan.entity.User;
import team.tunan.service.IUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tunan
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO user) {
        String userName = user.getUsername();
        String password = user.getPassword();
        System.out.println(password);
        if (StrUtil.isBlank(userName) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(user);
        return Result.success(dto);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO user) {
        String userName = user.getUsername();
        String password = DigestUtils.md5Hex(user.getPassword());
        if (StrUtil.isBlank(userName) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(user));
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param
     * @return
     */
    @GetMapping("/username/{username}")
    public Result findByUserName(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    /**
     * 获取用户信息
     *
     * @param
     * @return
     */
    @GetMapping
    public List<User> index() {
        return userService.list();
    }

    //新增或修改数据
    @PostMapping
    public Boolean save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //修改密码
    @PostMapping("/resetPassword")
    public Boolean resetPassword(@RequestBody UserDTO user) {
        System.out.println(user);
        return userService.resetPassword(user);
    }

    //修改状态
    @PostMapping("/updateState")
    public Boolean updateState(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return userService.updateState(userDTO);
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public Boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param userName
     * @param degree
     * @param rule
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String userName,
                           @RequestParam(defaultValue = "") String degree,
                           @RequestParam(defaultValue = "") String role) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!userName.equals("")) {
            queryWrapper.like("username", userName);
        }
        if (!degree.equals("")) {
            queryWrapper.like("degree", degree);
        }
        if (!role.equals("")) {
            queryWrapper.eq("role", role);
        }
        queryWrapper.orderByDesc("user_id");
        return Result.success(userService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}