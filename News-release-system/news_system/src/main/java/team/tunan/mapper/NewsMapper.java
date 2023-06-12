package team.tunan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import team.tunan.entity.News;

/**
 *
 * @author Tunan
 * @since 2023-05-08
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {
    //根据userid查询
    IPage<News> selectByUserId(IPage<News> page, Integer userId);
}
