package com.atguigu.springboot.config;

import com.atguigu.springboot.componment.LoginHandlerInterceptor;
import com.atguigu.springboot.componment.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.file.Watchable;
import java.util.Locale;

/**
 * @author 王龙飞
 * @version 1.0
 * @title: MyMvcConfig
 * @projectName spring-boot-web-crud
 * @description: 使用WebMvcConfigurerAdapter可以拓展Springmvc的功能
 * @date 2020/10/13   14:38
 */

//既保留了所有的自动配置，也能用我们拓展的功能

//@EnableWebMvc 会使自动配置文件失效
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        //浏览器发送、atguigu请求来到success页面
        registry.addViewController("/atguigu").setViewName("success");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    //所有的webMvcCOnfiguretionAdapter组件都会一起起作用 视图映射
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer adapter = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                super.addInterceptors(registry);
                // "/**"表示拦截任意多层路径的任意请求
                // excludePathPatterns表示不过滤掉那些请求
                //静态资源： *.css *.js
                // Springboot做好了静态资源映射
                // springboot2.0之后如果想要自定义的话就不可以了，需要手动放行静态资源。
                // 此处的addPathPatterns("**")不要使用 “/**”，否则静态资源还是会被拦截
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("**")
                        .excludePathPatterns("/index.html","/","/user/login")
                        .excludePathPatterns("/static/**");
            }
        };
        return adapter;
    }

    //提供区域解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


}
