package team.tunan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.tunan.dto.NewsTypeCategoryDTO;

import java.util.List;

/**
 * @author Tunan
 * @version 1.0
 */
@Mapper
public interface NewsTypeCategoryMapper {
    /**
     * 获取指定新闻类型下的所有新闻分类以及对应的展示内容
     *
     * @param newsTypeName 新闻类型名称
     * @return 新闻类型分类列表
     */
    @Select("SELECT n.name AS news_type_name, c.name AS news_category_name, c.displayContent " +
            "FROM news_type n " +
            "JOIN news_category c ON n.id = c.newsTypeId " +
            "WHERE n.name = #{newsTypeName}")
    List<NewsTypeCategoryDTO> getNewsTypeCategories(@Param("newsTypeName") String newsTypeName);
}
