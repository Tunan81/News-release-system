package team.tunan.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.tunan.entity.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tunan
 * @since 2023-05-20
 */
@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

}
