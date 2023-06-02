                                                                                                                                                                                                                                                                             package team.tunan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.tunan.dto.NewsCategoryDTO;
import team.tunan.entity.NewsCategory;
import team.tunan.mapper.NewsCategoryMapper;
import team.tunan.service.INewsCategoryService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tunan
 * @since 2023-05-20
 */
@Service
public class NewsCategoryServiceImpl extends ServiceImpl<NewsCategoryMapper, NewsCategory> implements INewsCategoryService {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<NewsCategoryDTO> queryNewCategoryByNewsTypeDd(Integer newsTypeId) {
        List<NewsCategory> list = list(new QueryWrapper<NewsCategory>().select(ID, NAME, DISPLAY_CONTENT).eq(NEWS_TYPE_ID, newsTypeId));
        return modelMapper.map(list, new TypeToken<List<NewsCategoryDTO>>(){}.getType());
    }
}
