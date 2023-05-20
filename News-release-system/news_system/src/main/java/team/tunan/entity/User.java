package team.tunan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Tunan
 * @since 2023-03-28
 */
@Data
@ApiModel(value = "User对象", description = "")
@TableName("user")
public class User implements Serializable {

    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String sex;

    private String hobbit;

    private String degree;

    private String note;

    private String role;

    private Integer status;
    private String password;
    private String avatar;

}
