package team.tunan.service;

import team.tunan.dto.NewsPaging;
import team.tunan.dto.NewsQueryDTO;
import team.tunan.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tunan
 * @since 2023-05-08
 */
public interface INewsService extends IService<News> {

    String PICTURE_PATH = "picture_path";

    String ID = "id";

    String TITLE = "title";

    String RELEASE_TIME = "release_time";

    String HITS = "hits";

    String CONTENT = "content";

    String NEWS_CATEGORY_ID = "news_category_id";

    String CREATE_TIME = "create_time";

    String UPDATE_TIME = "update_time";
    /**
     * 根据 NEWS_CATEGORY_ID 获取 新闻展示列表
     */
    NewsPaging getNewsListByNewsCategoryId(Integer newsCategoryId, Integer current, Integer size);

    /**
     * 根据 ID 获取 新闻详情
     */
    NewsQueryDTO getNewsById(Integer id);
}
