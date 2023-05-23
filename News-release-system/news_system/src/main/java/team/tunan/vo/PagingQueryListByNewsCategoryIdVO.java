package team.tunan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/5/23 10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingQueryListByNewsCategoryIdVO {

    /**
     * 新闻分类id
     */
    private Integer newsCategoryId;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 大小
     */
    private Integer size;
}
