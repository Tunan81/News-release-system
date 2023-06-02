package team.tunan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.tunan.common.DisplayContent;

/**
 * @author Tunan
 * @date 2023/5/31 22:05
 * @description 新闻类别
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCategoryDTO {

    private Integer id;

    private String name;

    private Integer newsTypeId;

    private DisplayContent displayContent;
}
