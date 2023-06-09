package team.tunan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tunan
 * @since 2023-05-08
 */
@Getter
@Setter
@ApiModel(value = "News对象", description = "")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("新闻")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("类别")
    private String type;

    @ApiModelProperty("发布时间")
    private Date releaseTime;

    @ApiModelProperty("发布人")
    private Integer userId;

    @ApiModelProperty("状态（0-未审核，1-审核通过，2-审核通过）")
    private Integer status;

    @ApiModelProperty("是否头条（0-否，1-是）")
    private Integer top;

    @ApiModelProperty("游览量")
    private Integer hits;

    @ApiModelProperty("点赞量")
    private Integer likes;

    @ApiModelProperty("封面图片")
    private String coverImage;

    private Integer isAudit;

    @ApiModelProperty("新闻类别")
    private Integer newsCategoryId;
}
