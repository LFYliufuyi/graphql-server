package net.likeyun;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 基础配置类
 * @Author: lfy
 * @Date: 2020/1/3 10:41
 */
@Configuration
@PropertySource("classpath:application-base.properties")
public class BaseConfig implements WebMvcConfigurer {
    /**
     * 跨域支持
     *
     * @param registry 注册
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }
}
