package team.tunan.strategy;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/5/24 20:47
 * @description 上传策略接口
 */
public interface UploadStrategy {


    // 上传文件
    String uploadFile(MultipartFile file, final String filePath);

    // 上传编辑器文件
    //这里的Map<String, Object>是为了返回给前端一个json数据
    //是编辑器的一个固定格式
    Map<String, Object> uploadEditorFile(MultipartFile file, final String filePath);

    String uploadIconFile(MultipartFile file, final String filePath);
}
