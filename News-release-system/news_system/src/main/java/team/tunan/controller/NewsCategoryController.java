package team.tunan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.tunan.common.Result;
import team.tunan.dto.NewsCategoryDTO;
import team.tunan.dto.NewsTypeCategoryDTO;
import team.tunan.entity.NewsCategory;
import team.tunan.mapper.NewsTypeCategoryMapper;
import team.tunan.service.INewsCategoryService;
import team.tunan.vo.news.category.NewsCategoryListVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tunan
 * @since 2023-05-20
 */
@RestController
@RequestMapping("/newsCategory")
public class NewsCategoryController {

    @Autowired
    ModelMapper modelMapper;

    @Resource
    private INewsCategoryService newsCategoryService;

    @Autowired
    private NewsTypeCategoryMapper newsTypeCategoryMapper;

    @GetMapping("/newsTypeCategories")
    public Result getNewsTypeCategories(@RequestParam String newsTypeName) {
        List<NewsTypeCategoryDTO> newsTypeCategories = newsTypeCategoryMapper.getNewsTypeCategories(newsTypeName);
        return Result.success(newsTypeCategories);
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody NewsCategory newsCategory) {
        newsCategoryService.saveOrUpdate(newsCategory);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        newsCategoryService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        newsCategoryService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(newsCategoryService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(newsCategoryService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<NewsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(newsCategoryService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 根据新闻类型id（大标题id）查询新闻类别列表
     * @param id 新闻类型id（大标题id）
     * @return 新闻类别列表
     */
    @ApiOperation(value = "查询新闻类别列表根据新闻类型id（大标题id）")
    @GetMapping("/list/{typeId}")
    public Result queryByNewsTypeId(@PathVariable("typeId") Integer id) {
        List<NewsCategoryDTO> list = newsCategoryService.queryNewCategoryByNewsTypeDd(id);
        return Result.success(modelMapper.map(list, new TypeToken<List<NewsCategoryListVO>>(){}.getType()));
    }
}

