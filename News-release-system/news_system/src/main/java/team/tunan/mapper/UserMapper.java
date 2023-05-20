package team.tunan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import team.tunan.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tunan
 * @since 2023-03-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
