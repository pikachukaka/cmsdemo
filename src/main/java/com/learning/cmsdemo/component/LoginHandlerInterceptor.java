package com.learning.cmsdemo.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user=request.getSession().getAttribute("loginUser");
        if(user==null){
            request.setAttribute("loginMSG","没有权限请先登陆");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }else{
            return true;
        }
    }


}
