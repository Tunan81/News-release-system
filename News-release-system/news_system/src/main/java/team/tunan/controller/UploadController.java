package team.tunan.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.tunan.common.ResponseResult;
import team.tunan.common.Result;
import team.tunan.entity.Files;
import team.tunan.mapper.FileMapper;
import team.tunan.strategy.context.UploadStrategyContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
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
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;

    /**
     * 文件上传至腾讯云
     *
     * @param storageType 存储类型
     * @param file        文件
     * @return
     */
    @PostMapping("/upload/cos")
    public ResponseResult<?> uploadToCos(MultipartFile file) {
        return ResponseResult.success("文件上传成功！",
                uploadStrategyContext.executeUploadStrategy(file, "/file/", "cosUploadServiceImpl"));
    }

    /**
     * 文件上传至本地
     *
     * @param storageType 存储类型
     * @param file        文件
     * @return
     */
    @PostMapping("/upload/local")
    public ResponseResult<?> uploadToLocal(MultipartFile file) {
        return ResponseResult.success("文件上传成功！",
                uploadStrategyContext.executeUploadStrategy(file, "/file/", "localUploadServiceImpl"));
    }

    /**
     * 分页查询接口
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示的记录数
     * @param name     文件名
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

    /**
     * 头像上传接口
     *
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null) {
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://" + serverIp + ":9090/file/" + fileUUID;
        }

        // 存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024); // 单位 kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        return url;
    }

    /**
     * 文件下载接口   http://localhost:9090/file/{fileUUID}
     *
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File("http://localhost:9090/file/" + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    /**
     * 根据id查询文件详情
     *
     * @param id 文件id
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(fileMapper.selectById(id));
    }

    /**
     * 批量删除文件
     *
     * @param id 文件id
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files file : files) {
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }

    private Files getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }
}