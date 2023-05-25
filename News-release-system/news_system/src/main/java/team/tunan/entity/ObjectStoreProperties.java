package team.tunan.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/5/24 20:47
 * @description 自定义配置文件
 */
@Getter
@Setter
@Component
@ConfigurationProperties("application.store")
public class ObjectStoreProperties {
    /**
     * cos配置
     */
    private ConfigEntity cos;

    /**
     * 本地配置
     */
    private ConfigEntity local;

    @Getter
    @Setter
    public static class ConfigEntity {

        /**
         * 访问域名
         */
        private String domainUrl;

        /**
         * key
         */
        private String accessKey;

        /**
         * 密钥
         */
        private String accessKeySecret;

        /**
         * 地域节点
         */
        private String endpoint;

        /**
         * 存储桶名称
         */
        private String bucket;

    }

}
