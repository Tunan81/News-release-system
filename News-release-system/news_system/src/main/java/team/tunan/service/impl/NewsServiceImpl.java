package team.tunan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import team.tunan.dto.NewsPaging;
import team.tunan.dto.NewsQueryDTO;
import team.tunan.dto.NewsQueryListDTO;
import team.tunan.entity.News;
import team.tunan.mapper.NewsCategoryMapper;
import team.tunan.mapper.NewsMapper;
import team.tunan.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
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
     * @param newsCategoryId 新闻分类id
     * @param current 当前页
     * @param size 每页显示条数
     * @return 新闻分页列表
     */
    @Override
    public NewsPaging getNewsListByNewsCategoryId(Integer newsCategoryId, Integer current, Integer size) {
        Page<News> page = page(new Page<>(current, size), new QueryWrapper<News>().select(ID, TITLE, RELEASE_TIME).eq(NEWS_CATEGORY_ID, newsCategoryId).orderByDesc(RELEASE_TIME));
        NewsPaging newsPaging = modelMapper.map(page, NewsPaging.class);
        List<News> records = page.getRecords();
        newsPaging.setRecords(modelMapper.map(records, new TypeToken<List<NewsQueryListDTO>>(){}.getType()));
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
}
