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
import team.tunan.common.DisplayContent;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tunan
 * @since 2023-05-20
 * 新闻分类（二级标题）
 */
@Getter
@Setter
@TableName("news_category")
@ApiModel(value = "NewsCategory对象", description = "")
public class NewsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("新闻分类")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("所属新闻类型id")
    private Integer newsTypeId;

    @ApiModelProperty("分类展示内容")
    private DisplayContent displayContent;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


}
