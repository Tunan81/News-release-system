package team.tunan.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import team.tunan.common.Constants;
import team.tunan.dto.UserDTO;
import team.tunan.entity.Menu;
import team.tunan.entity.User;
import team.tunan.exception.ServiceException;
import team.tunan.mapper.RoleMapper;
import team.tunan.mapper.RoleMenuMapper;
import team.tunan.mapper.UserMapper;
import team.tunan.service.IMenuService;
import team.tunan.service.IUserService;
import team.tunan.utils.TokenUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tunan
 * @since 2023-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;
    /**
     * 保存用户
     * @param user
     * @return
     */
    @Override
    public Boolean saveUser(User user) {
        return saveOrUpdate(user);
    }


    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

    /**
     * 重置密码
     * @param userDTO
     * @return
     */
    @Override
    public Boolean resetPassword(UserDTO userDTO) {
        User userInfo = getUserInfo(userDTO);
        if (userInfo == null) {
            return false;
        }
        if (userInfo.getPassword().equals("123456")){
            return false;
        }
        userInfo.setPassword("123456");
        saveOrUpdate(userInfo);
        return true;
    }

    /**
     * 更新用户状态
     * @param userDTO
     * @return
     */
    @Override
    public Boolean updateState(UserDTO userDTO) {
        User userInfo = getUserInfo(userDTO);
        if (userInfo == null) {
            return false;
        }
        if (userInfo.getStatus() == 1){
            userInfo.setStatus(0);
        }else {
            userInfo.setStatus(1);
        }
        saveOrUpdate(userInfo);
        return true;
    }

    /**
     * 登录
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = getUserInfo(userDTO);
        if (user == null) {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
        if (user.getStatus() == 1) {
            //将user的属性拷贝到userDTO中
            BeanUtil.copyProperties(user,userDTO,true);
            //设置token
            String token = TokenUtils.genToken(user.getUserId().toString(),user.getPassword());
            userDTO.setToken(token);
            String role = user.getRole(); // 获取用户角色
            List<Menu> roleMenus = getRoleMenus(role); // 获取当前角色的菜单列表
            userDTO.setMenus(roleMenus); // 设置菜单列表
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已被禁用");
        }
    }

    /**
     * 注册
     * @param userDTO
     * @return
     */
    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true); // 把 userDTO 对象的属性 copy 到 one 对象中
            one.setAvatar("https://tunan81-images-1313478973.cos.ap-chengdu.myqcloud.com/InitialHead.jpg");
            one.setSex("男"); // 默认性别为男
            one.setStatus(1); // 默认状态为启用
            one.setPassword(DigestUtils.md5Hex(userDTO.getPassword())); // 对密码进行加密
            save(one);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    /**
     * 获取用户信息
     * @param userDTO
     * @return
     */
    public User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
        return one;
    }
}
