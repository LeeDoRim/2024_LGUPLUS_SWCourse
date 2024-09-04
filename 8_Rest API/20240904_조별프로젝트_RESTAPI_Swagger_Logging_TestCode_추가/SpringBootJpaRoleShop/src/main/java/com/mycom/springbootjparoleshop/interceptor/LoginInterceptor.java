package com.mycom.springbootjparoleshop.interceptor;

import com.mycom.springbootjparoleshop.user.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor >>> " + request.getRequestURI());

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if (userDto == null) {

            if ("true".equals(request.getHeader("ajax"))) {
                System.out.println("LoginInterceptor >>> ajax");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"redirect\":\"/login.html\"}");
            } else { // page(html) 요청
                System.out.println("LoginInterceptor >>> page");
                response.sendRedirect("/login.html");
            }

            return false;
        }

        return true;
    }

}
