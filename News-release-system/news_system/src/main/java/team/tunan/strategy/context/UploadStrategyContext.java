package team.tunan.strategy.context;


import team.tunan.strategy.UploadStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author: Tunan
 * @createDate:2023/5/24
 * @description: 上传策略上下文
 * @version： 1.0
 */
@Component
@RequiredArgsConstructor
public class UploadStrategyContext {

    private final Map<String, UploadStrategy> uploadStrategyMap;

    /**
     * 执行上传策略
     *
     * @param file     文件
     * @param filePath 文件上传路径前缀
     * @return {@link String} 文件上传全路径
     */
    public String executeUploadStrategy(MultipartFile file, final String filePath, String uploadServiceName) {
        // 执行特点的上传策略
        return uploadStrategyMap.get(uploadServiceName).uploadFile(file, filePath);
    }

    // 执行删除策略
    public void executeDeleteStrategy(String url, String uploadServiceName) {
       return;
    }

}
