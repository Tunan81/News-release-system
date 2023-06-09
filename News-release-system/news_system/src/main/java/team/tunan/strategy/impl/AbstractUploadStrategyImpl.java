package team.tunan.strategy.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import team.tunan.entity.Files;
import team.tunan.exception.BaseException;
import team.tunan.mapper.FileMapper;
import team.tunan.strategy.UploadStrategy;
import team.tunan.utils.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/05/24 20:59
 * @description 抽象上传策略类
 */
@Getter
@Setter
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public String uploadFile(MultipartFile file, String filePath) {
        try {

            //region 获取文件md5值 -> 获取文件后缀名 -> 生成相对路径
            String fileMd5 = FileUtil.getMd5(file.getInputStream()); // 文件md5值
            String extName = FileUtil.getExtName(file.getOriginalFilename()); // 文件后缀名
            String fileRelativePath = filePath + fileMd5 + extName; // 文件相对路径
            //endregion

            //region 初始化
            initClient();
            //endregion

            //region 检测文件是否已经存在，不存在则进行上传操作
            if (!checkFileIsExisted(fileRelativePath)) {
                executeUpload(file, fileRelativePath);
                // 保存文件信息到数据库
                Files files = new Files();
                files.setName(file.getOriginalFilename());
                files.setType(file.getContentType());
                files.setSize(file.getSize() / 1024); // 单位为kb
                files.setMd5(fileMd5); // 文件md5值
                files.setUrl(getPublicNetworkAccessUrl(fileRelativePath)); // 文件访问路径
                files.setSuffix(extName); // 文件后缀名
                fileMapper.insert(files);
            }
            //endregion

            return getPublicNetworkAccessUrl(fileRelativePath);
        } catch (IOException e) {
            throw new BaseException("文件上传失败");
        }
    }

    @Override
    public Map<String, Object> uploadEditorFile(MultipartFile file, String filePath) {
        try {
            //region 获取文件md5值 -> 获取文件后缀名 -> 生成相对路径
            String fileMd5 = FileUtil.getMd5(file.getInputStream()); // 文件md5值
            String extName = FileUtil.getExtName(file.getOriginalFilename()); // 文件后缀名
            String fileRelativePath = filePath + fileMd5 + extName; // 文件相对路径
            //endregion

            //region 初始化
            initClient();
            //endregion

            //region 检测文件是否已经存在，不存在则进行上传操作
            if (!checkFileIsExisted(fileRelativePath)) {
                executeUpload(file, fileRelativePath);
                // 保存文件信息到数据库
                Files files = new Files();
                files.setName(file.getOriginalFilename());
                files.setType(file.getContentType());
                files.setSize(file.getSize() / 1024); // 单位为kb
                files.setMd5(fileMd5); // 文件md5值
                files.setUrl(getPublicNetworkAccessUrl(fileRelativePath)); // 文件访问路径
                files.setSuffix(extName); // 文件后缀名
                fileMapper.insert(files);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, String> data = new HashMap<>();
            data.put("url", getPublicNetworkAccessUrl(fileRelativePath));
            data.put("alt", null);
            data.put("href", null);
            map.put("errno", 0);
            map.put("data", data);
            System.out.println(map.get("data"));
            return map;
        } catch (IOException e) {
            throw new BaseException("文件上传失败");
        }
    }

    @Override
    public String uploadIconFile(MultipartFile file, String filePath) {
        try {
            //region 获取文件md5值 -> 获取文件后缀名 -> 生成相对路径
            String fileMd5 = FileUtil.getMd5(file.getInputStream()); // 文件md5值
            String extName = FileUtil.getExtName(file.getOriginalFilename()); // 文件后缀名
            String fileRelativePath = filePath + fileMd5 + extName; // 文件相对路径
            //endregion

            //region 初始化
            initClient();
            //endregion

            //region 检测文件是否已经存在，不存在则进行上传操作
            if (!checkFileIsExisted(fileRelativePath)) {
                executeUpload(file, fileRelativePath);
                // 保存文件信息到数据库
                Files files = new Files();
                files.setName(file.getOriginalFilename());
                files.setType(file.getContentType());
                files.setSize(file.getSize() / 1024); // 单位为kb
                files.setMd5(fileMd5); // 文件md5值
                files.setUrl(getPublicNetworkAccessUrl(fileRelativePath)); // 文件访问路径
                files.setSuffix(extName); // 文件后缀名
                fileMapper.insert(files);
            }
            String url = getPublicNetworkAccessUrl(fileRelativePath);
            return url;
        } catch (IOException e) {
            throw new BaseException("文件上传失败");
        }
    }

    /**
     * 初始化客户端
     */
    public abstract void initClient();

    /**
     * 检查文件是否已经存在（文件MD5值唯一）
     *
     * @param fileRelativePath 文件相对路径
     * @return true 已经存在  false 不存在
     */
    public abstract boolean checkFileIsExisted(String fileRelativePath);

    /**
     * 执行上传操作
     *
     * @param file             文件
     * @param fileRelativePath 文件相对路径
     * @throws IOException io异常信息
     */
    public abstract void executeUpload(MultipartFile file, String fileRelativePath) throws IOException;

    /**
     * 获取公网访问路径
     *
     * @param fileRelativePath 文件相对路径
     * @return 公网访问绝对路径
     */
    public abstract String getPublicNetworkAccessUrl(String fileRelativePath);

}
