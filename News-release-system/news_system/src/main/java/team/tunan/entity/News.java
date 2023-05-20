package team.tunan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tunan
 * @since 2023-05-08
 */
@Getter
@Setter
@ApiModel(value = "News对象", description = "")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("新闻")
    @TableId(value = "nid", type = IdType.AUTO)
    private Integer nid;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("类别")
    private String type;

    @ApiModelProperty("发布时间")
    private LocalDateTime releaseTime;

    @ApiModelProperty("发布人")
    private Integer userId;

    @ApiModelProperty("状态（0-未审核，1-审核通过，2-审核通过）")
    private Integer status;

    @ApiModelProperty("是否头条（0-否，1-是）")
    private Integer top;

    @ApiModelProperty("游览量")
    private Integer views;

    @ApiModelProperty("点赞量")
    private Integer likes;

    @ApiModelProperty("封面图片")
    private String coverImage;

}
