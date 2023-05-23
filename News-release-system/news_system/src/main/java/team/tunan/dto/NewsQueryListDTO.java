package team.tunan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Tunan
 * @date 2023-05-23 15:36
 *
 * 查询新闻列表时返回的实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsQueryListDTO {

    private String picturePath;

    private Integer id;

    private String title;

    private Date releaseTime;
}
