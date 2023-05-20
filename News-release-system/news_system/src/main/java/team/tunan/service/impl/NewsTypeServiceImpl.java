package team.tunan.service.impl;

import team.tunan.entity.NewsType;
import team.tunan.mapper.NewsTypeMapper;
import team.tunan.service.INewsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tunan
 * @since 2023-05-20
 */
@Service
public class NewsTypeServiceImpl extends ServiceImpl<NewsTypeMapper, NewsType> implements INewsTypeService {

}
