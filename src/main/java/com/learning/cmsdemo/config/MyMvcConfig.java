package com.learning.cmsdemo.config;

import com.learning.cmsdemo.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/view1").setViewName("/login");
        registry.addViewController("/view2").setViewName("/register");
        registry.addViewController("/main.html").setViewName("/operate");
        registry.addViewController("/login.html").setViewName("/login");
        registry.addViewController("/operate1.html").setViewName("/operate");
        registry.addViewController("/join/submit").setViewName("/operate");
        registry.addViewController("/admin.html").setViewName("/admin");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html","/index.html","/view2","/static/**","/","/view1","/user/main","/user/login");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
