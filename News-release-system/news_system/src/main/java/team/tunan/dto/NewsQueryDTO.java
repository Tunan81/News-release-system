package team.tunan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Tunan
 * @date 2023/05/23
 *
 * 查询新闻主体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsQueryDTO {

    private Integer id;

    private String title;

    private Date releaseTime;

    private Integer hits;

    private String content;
}
