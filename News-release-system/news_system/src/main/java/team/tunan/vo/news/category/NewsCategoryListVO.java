package team.tunan.vo.news.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.tunan.common.DisplayContent;
/**
 * @author xmp
 * @date 2022-11-19 14:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCategoryListVO {

    private Integer id;

    private String name;

    private DisplayContent displayContent;
}
