package hello.itemservice.web;

import hello.itemservice.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")          // 모든 URL에 적용
                .excludePathPatterns(            // 로그인 없이 접근 가능한 URL
                        "/", "/members/add", "/login", "/logout", "/css/**"
                );
    }
}
