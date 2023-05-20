package team.tunan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import team.tunan.entity.Files;

@Mapper
public interface FileMapper extends BaseMapper<Files> {
}
