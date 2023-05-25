package team.tunan.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import team.tunan.common.Result;

import team.tunan.dto.NewsQueryDTO;
import team.tunan.service.INewsService;
import team.tunan.entity.News;

import org.springframework.web.bind.annotation.RestController;
import team.tunan.vo.PagingQueryListByNewsCategoryIdVO;

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

    @Autowired
    private ModelMapper  modelMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody News news) {
        news.setHits(0); // 点击量默认为0
        news.setReleaseTime(new Date()); // 发布时间
        newsService.saveOrUpdate(news);
        return Result.success();
    }

    /**
     * 新增
     * @param news 新闻实体
     * @return
     */
    @PostMapping("/add")
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
    public Boolean delete(@PathVariable Integer id) {
        return newsService.removeById(id);
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
                           @RequestParam(defaultValue = "") String isAudit) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (title != null && !"".equals(title)) {
            queryWrapper.like("title", title);
        }
        if (type != null && !"".equals(type)) {
            queryWrapper.eq("type", type);
        }
        if (isAudit != null && !"".equals(isAudit)) {
            queryWrapper.eq("status", isAudit);
        }
        return Result.success(newsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    /**
     * 分页查询 通过用户id
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param title 标题
     * @param type 类型
     * @param status 状态
     * @return
     */
    @GetMapping("/pageByUserId")
    public Result findPageByUserId(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String title,
                           @RequestParam(defaultValue = "") String type,
                           @RequestParam(defaultValue = "") String isAudit,
                           @RequestParam(defaultValue = "") Integer userId) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (title != null && !"".equals(title)) {
            queryWrapper.like("title", title);
        }
        if (type != null && !"".equals(type)) {
            queryWrapper.eq("type", type);
        }
        if (isAudit != null && !"".equals(isAudit)) {
            queryWrapper.eq("status", isAudit);
        }
        if (userId != null && !"".equals(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        return Result.success(newsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     *  分页查询指定小标题下的新闻列表，可指定当前页和每页条数
     * @param vo 小标题id，当前页，每页条数
     * @return 新闻列表
     */
    @ApiOperation(value = "分页查询指定小标题下的新闻列表，可指定当前页和每页条数")
    @PostMapping("/list")
    public Result pagingQueryListByNewsCategoryId(PagingQueryListByNewsCategoryIdVO vo) {
        return Result.success(newsService.getNewsListByNewsCategoryId(vo.getNewsCategoryId(), vo.getCurrent(), vo.getSize()));

    }

    /**
     *  根据id获取新闻主题
     * @param id 新闻主题id
     * @return 新闻主题
     */
    @ApiOperation("获取新闻主题根据id")
    @GetMapping("/main/{id}")
    public Result queryNewsById(@PathVariable("id") Integer id) {
        NewsQueryDTO news = newsService.getNewsById(id);
        return Result.success(news);
    }
}

