package team.tunan.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import team.tunan.entity.Menu;

import java.util.List;

/**
 * @author Tunan
 * @version 1.0
 * 接收前端请求的参数
 * @date 2021/4/23 10:47
 * @description 用户DTO
 */
@Data
// 当该属性为null时，不返回该属性
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @TableField("user_id")
    private Integer userId;
    @TableField("username")
    private String username;
    private String password;
    private String avatar;
    private String token;
    private Integer status;
    private String role;
    private List<Menu> menus;
}
