package com.mycom.springbootjparoleshop.interceptor;

import com.mycom.springbootjparoleshop.user.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class RoleBasedAccessInterceptor implements HandlerInterceptor {
    static final String ADMIN_ROLE_NAME = "role_admin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("RoleBasedAccessInterceptor >>> " + request.getRequestURI());

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if (userDto != null) {
            Map<Long, String> roles = userDto.getRoles();
            if (!roles.containsValue(ADMIN_ROLE_NAME)) { // 관리자가 아닌 경우

                if ("true".equals(request.getHeader("ajax"))) {
                    System.out.println("RoleBasedAccessInterceptor >>> ajax");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"redirect\":\"/access-denied.html\"}");
                } else {
                    System.out.println("RoleBasedAccessInterceptor >>> page");
                    response.sendRedirect("/access-denied.html");
                }

                return false;
            }

        }
        return true;
    }
}