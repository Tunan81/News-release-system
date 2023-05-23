package team.tunan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * @author Tunan
 * @date 2023-05-23 17:36
 * <p>
 * 新闻分页类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsPaging {

    /**
     * 查询数据列表
     */
    List<Object> records = Collections.emptyList();

    /**
     * 总数
     */
    long total;

    /**
     * 每页显示条数，默认 10
     */
    long size;

    /**
     * 当前页
     */
    long current;

    /**
     * 是否存在上一页
     */
    boolean previous;

    /**
     * 是否存在下一页
     */
    boolean hasNext;
}
