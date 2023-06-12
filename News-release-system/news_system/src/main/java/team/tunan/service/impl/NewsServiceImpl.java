package team.tunan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.tunan.dto.NewsPaging;
import team.tunan.dto.NewsQueryDTO;
import team.tunan.dto.NewsQueryListDTO;
import team.tunan.entity.News;
import team.tunan.mapper.NewsMapper;
import team.tunan.service.INewsService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Tunan
 * @since 2023-05-08
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {


    @Autowired
    ModelMapper modelMapper;

    /**
     * 根据新闻分类id查询新闻列表
     *
     * @param newsCategoryId 新闻分类id
     * @param current        当前页
     * @param size           每页显示条数
     * @return 新闻分页列表
     */
    @Override
    public NewsPaging getNewsListByNewsCategoryId(Integer newsCategoryId, Integer current, Integer size) {
        Page<News> page = page(new Page<>(current, size), new QueryWrapper<News>().select(ID, TITLE, RELEASE_TIME).eq(NEWS_CATEGORY_ID, newsCategoryId).eq(IS_AUDIT,1).orderByDesc(RELEASE_TIME));
        NewsPaging newsPaging = modelMapper.map(page, NewsPaging.class);
        List<News> records = page.getRecords();
        newsPaging.setRecords(modelMapper.map(records, new TypeToken<List<NewsQueryListDTO>>() {
        }.getType()));
        return newsPaging;
    }

    @Override
    public NewsPaging getTopNewsList(Integer current, Integer size) {
        // 查询置顶的新闻
        Page<News> page = page(new Page<>(current, size), new QueryWrapper<News>().select(ID, TITLE, RELEASE_TIME,COVER_IMAGE).eq(TOP, 1).eq(IS_AUDIT,1).orderByDesc(RELEASE_TIME));
        // 将查询到的数据转换为 NewsPaging
        NewsPaging newsPaging = modelMapper.map(page, NewsPaging.class);
        // 将查询到的数据转换为 NewsQueryListDTO
        List<News> records = page.getRecords();
        // 将转换后的数据设置到 NewsPaging 中
        newsPaging.setRecords(modelMapper.map(records, new TypeToken<List<NewsQueryListDTO>>() {
        }.getType()));
        return newsPaging;
    }

    public NewsQueryDTO getNewsById(Integer id) {
        // 新闻点击率加一
        News one = getOne(new QueryWrapper<News>().select(HITS).eq(ID, id));
        if (one == null) {
            return null;
        }
        update(new UpdateWrapper<News>().set(HITS, one.getHits() + 1).eq(ID, id));
        return modelMapper.map(getById(id), NewsQueryDTO.class);
    }

    @Override
    public Boolean updateNewsTopById(News news) {
        //先判断是否已经指定，再查询新闻热门数量是否大于5,如果大于5,则不允许置顶
        News one = getOne(new QueryWrapper<News>().select(TOP).eq(ID, news.getId()));
        if (one.getTop() == 1) {
            return update(new UpdateWrapper<News>().set(TOP, 0).eq(ID, news.getId()));
        }
        Integer count = count(new QueryWrapper<News>().eq(TOP, 1));
        if (count >= 5) {
            return false;
        }
        return update(new UpdateWrapper<News>().set(TOP, 1).eq(ID, news.getId()));
    }


    public News getNewsInfobyId(News news) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", news.getId());
        News one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            return null;
        }
        return one;
    }
}
