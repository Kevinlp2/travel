package com.lp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String uri = req.getRequestURI();
            String methodName = uri.substring( uri.lastIndexOf( "/" ) +1);
            Method method = this.getClass().getMethod( methodName, HttpServletRequest.class, HttpServletResponse.class );
            method.invoke( this,req,resp );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
