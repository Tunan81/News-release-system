package team.tunan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Tunan
 * @version 1.0
 * @date 2021/4/18 10:47
 * @description 角色菜单关联表
 */
@Data
@TableName("role_menu")
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
}
