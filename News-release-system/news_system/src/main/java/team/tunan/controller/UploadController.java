package team.tunan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import team.tunan.common.ResponseResult;
import team.tunan.common.Result;
import team.tunan.entity.Files;
import team.tunan.mapper.FileMapper;
import team.tunan.strategy.context.UploadStrategyContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件上传接口
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class UploadController {

    private final UploadStrategyContext uploadStrategyContext;

    @Resource
    private FileMapper fileMapper;

    /**
     * 文件上传至腾讯云
     * @param storageType 存储类型
     * @param file 文件
     * @return
     */
    @PostMapping("/upload/cos")
    public ResponseResult<?> uploadToCos(MultipartFile file) {
        return ResponseResult.success("文件上传成功！",
                uploadStrategyContext.executeUploadStrategy(file,"/file/","cosUploadServiceImpl"));
    }

    /**
     * 文件上传至本地
     * @param storageType 存储类型
     * @param file 文件
     * @return
     */
    @PostMapping("/upload/local")
    public ResponseResult<?> uploadToLocal(MultipartFile file) {
        return ResponseResult.success("文件上传成功！",
                uploadStrategyContext.executeUploadStrategy(file,"/file/","localUploadServiceImpl"));
    }

    /**
     * 分页查询接口
     * @param pageNum 当前页码
     * @param pageSize 每页显示的记录数
     * @param name 文件名
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam(required = false) Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {

        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        // 查询未删除的记录
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/detail/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(fileMapper.selectById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        // select * from sys_file where id in (id,id,id...)
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files file : files) {
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }
}
