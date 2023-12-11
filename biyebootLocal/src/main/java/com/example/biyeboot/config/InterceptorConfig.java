//package com.example.biyeboot.config;
//
//import com.example.biyeboot.common.interceptor.JwtInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Autowired
//    JwtInterceptor jwtInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//       registry.addInterceptor(jwtInterceptor)
//               .addPathPatterns("/**")
//               .excludePathPatterns("/user/login","/user/register","/alipay/**");//拦截所有请求，通过判断token是否合法决定登录与否
//    }
//
//}
