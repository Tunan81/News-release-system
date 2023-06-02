package team.tunan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/6/1 20:20
 * @description 图标实体类
 */
@Data
@TableName("dict")
public class Dict {
    private String name;
    private String value;
    private String type;
}
