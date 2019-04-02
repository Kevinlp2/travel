package com.lp.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.entity.Favorite;
import com.lp.entity.Route;
import com.lp.service.Impl.RouteServiceImpl;
import com.lp.service.Impl.UserServiceImpl;
import com.lp.service.RouteService;
import com.lp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/seeServlet")
public class seeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute( "userid" );
        String rid = request.getParameter( "rid" );

//        System.out.println(rid);

        RouteService service = new RouteServiceImpl();
        if(userid!=null){
            //获取该用户的收藏
            UserService userService = new UserServiceImpl();
            List<Favorite> favoriteList = userService.selectFavo( Integer.parseInt( userid ) );
            //该用户是否收藏
            int ifshou=0;
            for (Favorite f:favoriteList) {
                if(f.getRid()==Integer.parseInt(rid)){
                    ifshou=1;
                }
            }
            session.setAttribute( "ifshou",ifshou );
        }



        Route route = service.seeRoute( Integer.parseInt(rid) );



        session.setAttribute( "route",route );

        response.sendRedirect( "route_detail.jsp" );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost( request,response );
    }
}
