package team.tunan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.tunan.config.interceptor.JwtInterceptor;

/**
 * 拦截器配置
 * 1. 拦截所有请求，通过判断token是否合法来决定是否需要登录
 * 2. 放行静态文件
 * 3. 放行swagger相关
 * 4. 放行导入导出接口
 * 5. 放行登录注册接口
 * 6. 放行文件上传接口
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @param registry 注册器
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor())
//                .addPathPatterns("/**")  // 拦截所有请求，通过判断token是否合法来决定是否需要登录
//                .excludePathPatterns("common/**","/news/list","/news/main/**","/user/login", "/user/register", "/**/export", "/**/import", "/file/**",
//                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/api/", "/api-docs", "/api-docs/**")
//                .excludePathPatterns("/**/*.html", "/**/*.js", "/**/*.css", "/**/*.woff", "/**/*.ttf");  // 放行静态文件
//
//    }

    /**
     * 注册拦截器Bean
     * @return JwtInterceptor
     */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}
