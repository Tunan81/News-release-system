package team.tunan.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import team.tunan.common.Result;

import team.tunan.service.INewsService;
import team.tunan.entity.News;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tunan
 * @since 2023-05-08
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private INewsService newsService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody News news) {
        newsService.saveOrUpdate(news);
        return Result.success();
    }

    @PostMapping("/addNews")
    public Result addNews(@RequestBody News news) {
        newsService.saveOrUpdate(news);
        return Result.success();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        newsService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        newsService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping
    public Result findAll() {
        return Result.success(newsService.list());
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(newsService.getById(id));
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param title
     * @param type
     * @param status
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String title,
                           @RequestParam(defaultValue = "") String type,
                           @RequestParam(defaultValue = "") String status) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("nid");
        if (title != null && !"".equals(title)) {
            queryWrapper.like("title", title);
        }
        if (type != null && !"".equals(type)) {
            queryWrapper.eq("type", type);
        }
        if (status != null && !"".equals(status)) {
            queryWrapper.eq("status", status);
        }
        return Result.success(newsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

