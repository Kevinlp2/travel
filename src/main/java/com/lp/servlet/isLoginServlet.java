package com.lp.servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.entity.Category;
import com.lp.service.CategoryService;
import com.lp.service.Impl.CategoryServiceImpl;
import com.lp.untils.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/isLoginServlet")
public class isLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute( "username","" );
        session.removeAttribute( "userid" );
        Jedis jedis = JedisUtil.getJedis();
        String category = jedis.get( "category" );
        ObjectMapper mapper = new ObjectMapper();
        if(category != null){
            List<Category> categorylist =  mapper.readValue( category, new TypeReference<List<Category>>() {} );
            session.setAttribute( "categorys",categorylist );
        }else {
            CategoryService service = new CategoryServiceImpl();
            List<Category> categoryList = service.selectAll();
            String json = mapper.writeValueAsString( categoryList );
            jedis.set( "category",json );
            jedis.close();
        }
        response.sendRedirect( "login.jsp" );
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost( request,response );
    }
}
