package top.upstudy.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.upstudy.crm.interceptor.UnLoginInterceptor;

/**
 * 1、设置拦截器放行和不放行的页面，并使其生效
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public UnLoginInterceptor unLoginInterceptor(){
        return new UnLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(unLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/error","/file","/index","/user/login","/css/**","/images/**","/js/**","/lib/**");

    }


}
