package team.tunan.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/5/24 20:47
 * @description 上传策略接口
 */
public interface UploadStrategy {

    String uploadFile(MultipartFile file, final String filePath);

    Boolean deleteFile(String url); //删除接口
}
