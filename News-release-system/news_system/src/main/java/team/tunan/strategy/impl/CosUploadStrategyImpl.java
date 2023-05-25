package team.tunan.strategy.impl;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.tunan.entity.ObjectStoreProperties;
import team.tunan.strategy.impl.AbstractUploadStrategyImpl;

import java.io.IOException;

/**
 * @author: Tunan
 * @createDate: 2023/05/24
 * @description: 腾讯云Cos文件上传策略实现类
 * @version： 1.0
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service("cosUploadServiceImpl")
public class CosUploadStrategyImpl extends AbstractUploadStrategyImpl {

    /**
     * 构造器注入
     */
    private final ObjectStoreProperties properties;

    /**
     * 属性
     */
    private COSClient cosClient;

    /**
     * 初始化客户端
     */
    @Override
    public void initClient() {

        COSCredentials cred = new BasicCOSCredentials(properties.getCos().getAccessKey(), properties.getCos().getAccessKeySecret());
        //region ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(properties.getCos().getEndpoint()));
        clientConfig.setHttpProtocol(HttpProtocol.http);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionTimeout(30 * 1000);

        // 生成 cos 客户端
        cosClient = new COSClient(cred, clientConfig);
        log.info("CosClient Init Success...");
    }

    /**
     * 检查文件是否存在
     * @param fileRelativePath 文件相对路径
     * @return
     */
    @Override
    public boolean checkFileIsExisted(String fileRelativePath) {
        return cosClient.doesObjectExist(properties.getCos().getBucket(), fileRelativePath);
    }

    /**
     * 执行文件上传
     * @param file 文件
     * @param fileRelativePath 文件相对路径
     * @throws IOException IO异常
     */
    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath) throws IOException {
        log.info("File Upload Starts...");
        cosClient.putObject(properties.getCos().getBucket(), fileRelativePath, file.getInputStream(), null);
        log.info("File Upload Finish...");
    }

    /**
     * 获取文件的公网访问地址
     * @param fileRelativePath 文件相对路径
     * @return 公网访问地址
     */
    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath) {
        return properties.getCos().getDomainUrl() + fileRelativePath;
    }
}
