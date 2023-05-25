package team.tunan.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tunan
 * @date 2023/5/23 10:47
 * @description ModelMapper配置类
 * @since version-1.0
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // 完全匹配模式
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        // 匹配策略使用严格模式
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
