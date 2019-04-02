package com.lp.filter;

import com.lp.entity.User;
import com.lp.service.Impl.UserServiceImpl;
import com.lp.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1、获取requst和response 对象
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        //2、获取请求地址
        String uri = request.getRequestURI();
        String username = (String) session.getAttribute( "username" );
        if(username ==""){

        }

        if(uri.contains("index.jsp")){
            if(username != ""){
                chain.doFilter(req, resp);
            }else{
                //如果没有登录
                request.getSession().setAttribute( "msg","请先登录" );
                response.sendRedirect( "login.jsp" );
            }
        }else if(uri.contains("login.jsp")){

            if(username==""){
                // 获取请求携带的cookies
                Cookie[] cookies = request.getCookies();
                Cookie cookie = null;
                // 先要判断获取的cookies是否存在，不然遍历的时候会报空指针异常
                if (cookies != null && cookies.length > 0) {
                    // 遍历cookies
                    for (Cookie c : cookies) {
                        String name = c.getName();
                        if ("autoLogin".equals(name)) {
                            // 说明cookie中存在自动登录信息
                            cookie = c;
                            break;
                        }
                    }
                }
                try {
                    // 说明存在自动登陆的cookie
                    if (cookie != null) {
                        // 我们需要把cookie的值读取出来存放到session中
                        String[] split = cookie.getValue().split("&");
                        // 调用service层登陆
                        UserService userService = new UserServiceImpl();
                        Map<String, String> map = userService.login( split[0], split[1] );
                        Set<String> set = map.keySet();
                        for (String s:set) {
                            if(s.equals( "1" )){
                                session.setAttribute( "usernaem",split[0] );
                                response.sendRedirect( "index.jsp" );
                            }
                        }
                    }else {
                        chain.doFilter(req, resp);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }else {
            chain.doFilter(req, resp);
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
