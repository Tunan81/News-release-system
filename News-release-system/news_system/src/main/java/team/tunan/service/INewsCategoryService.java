package team.tunan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.dto.NewsCategoryDTO;
import team.tunan.entity.NewsCategory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tunan
 * @since 2023-05-20
 */
public interface INewsCategoryService extends IService<NewsCategory> {
    String ID = "id";

    String NAME = "name";

    String NEWS_TYPE_ID = "news_type_id";
    String DISPLAY_CONTENT = "display_content";

    /**
     * 按 NEWS_TYPE_ID 查找新闻类别
     */
    List<NewsCategoryDTO> queryNewCategoryByNewsTypeDd(Integer newsTypeId);

}
