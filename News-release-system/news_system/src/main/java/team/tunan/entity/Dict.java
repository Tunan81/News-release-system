package team.tunan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Tunan
 * @version 1.0
 */
@Data
@TableName("dict")
public class Dict {
    private String name;
    private String value;
    private String type;
}
