package team.tunan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tunan
 * @since 2023-05-20
 * 新闻类型（一级标题）
 */
@Getter
@Setter
@TableName("news_type")
@ApiModel(value = "NewsType对象", description = "")
public class NewsType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("新闻类型")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类型名称")
    private String name;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


}
