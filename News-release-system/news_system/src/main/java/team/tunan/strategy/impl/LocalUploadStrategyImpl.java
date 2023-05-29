package team.tunan.strategy.impl;

import team.tunan.exception.BaseException;
import team.tunan.entity.ObjectStoreProperties;
import team.tunan.common.HttpCodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import team.tunan.strategy.impl.AbstractUploadStrategyImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Tunan
 * @createDate: 2023/5/24
 * @description: 本地上传策略实现
 * @version： 1.0
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service("localUploadServiceImpl")
public class LocalUploadStrategyImpl extends AbstractUploadStrategyImpl {

    /**
     * 本地项目端口
     */
    @Value("${server.port}")
    private Integer port;

    /**
     * 前置路径 ip/域名
     */
    private String prefixUrl;

    /**
     * 构造器注入bean
     */
    private final ObjectStoreProperties properties;

    /**
     * 初始化客户端
     * @throws FileNotFoundException 文件不存在异常
     * @throws BaseException         自定义异常
     */
    @Override
    public void initClient() {
        try {
            prefixUrl = ResourceUtils.getURL("classpath:").getPath() + "static/";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BaseException("文件不存在");
        }
        log.info(prefixUrl);
        log.info("CosClient Init Success...");
    }

    /**
     * 检查文件是否存在
     * @param fileRelativePath 文件相对路径
     * @return {@link Boolean} 是否存在
     */
    @Override
    public boolean checkFileIsExisted(String fileRelativePath) {
        return new File(prefixUrl + fileRelativePath).exists();
    }

    /**
     * 执行上传
     * @param file             文件
     * @param fileRelativePath 文件相对路径
     * @throws IOException io异常
     */
    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath) throws IOException {
        File dest = checkFolderIsExisted(fileRelativePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("文件上传失败");
        }
    }

    /**
     *
     * @param fileRelativePath 文件相对路径
     * @return
     */
    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            if (StringUtils.isEmpty(properties.getLocal().getDomainUrl())) {
                return String.format("http://%s:%d%s", host, port, fileRelativePath);
            }
            return properties.getLocal().getDomainUrl() + fileRelativePath;
        } catch (UnknownHostException e) {
            throw new BaseException(HttpCodeEnum.UNKNOWN_ERROR);
        }
    }


    /**
     * 检查文件夹是否存在，若不存在则创建文件夹，最终返回上传文件
     *
     * @param fileRelativePath 文件相对路径
     * @return {@link  File} 文件
     */
    private File checkFolderIsExisted(String fileRelativePath) {
        File rootPath = new File(prefixUrl + fileRelativePath);
        if (!rootPath.exists()) {
            if (!rootPath.mkdirs()) {
                throw new BaseException("文件夹创建失败");
            }
        }
        return rootPath;
    }

}
