package com.mycom.springbootjparoleshop.config;

import com.mycom.springbootjparoleshop.interceptor.LoginInterceptor;
import com.mycom.springbootjparoleshop.interceptor.RoleBasedAccessInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMVCConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final RoleBasedAccessInterceptor roleBasedAccessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/index.html",
                        "/login.html",
                        "/register.html",
                        "/users/login",
                        "/users/register/**"
                );

        registry.addInterceptor(roleBasedAccessInterceptor)
                .addPathPatterns(
                        "/admin.html",
                        "/admin/**",
                        "/admin_editProduct.html",
                        "/admin_insertProduct.html",
                        "/admin_orderList.html",
                        "/admin_productList.html",
                        "/admin_userList.html"
                );
    }
}
