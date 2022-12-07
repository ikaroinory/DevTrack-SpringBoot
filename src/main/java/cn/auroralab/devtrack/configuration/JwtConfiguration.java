package cn.auroralab.devtrack.configuration;

import cn.auroralab.devtrack.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtConfiguration implements WebMvcConfigurer {
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .excludePathPatterns("/error")
                .addPathPatterns("/**");
    }
}
