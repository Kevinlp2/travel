package com.lp.servlet;

import com.lp.entity.PageBean;
import com.lp.service.Impl.RouteServiceImpl;
import com.lp.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 收藏排行榜
 */
@WebServlet("/favorServlet")
public class FavorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setContentType("text/html;charset=utf-8");
        String currentPage = request.getParameter( "currentPage" );
        //获取搜索框信息
        String routename = request.getParameter( "routername" );
        String routeprice1 = request.getParameter( "routeprice1" );
        String routeprice2 = request.getParameter( "routeprice2" );
        String cid = request.getParameter( "cid" );

        RouteService service = new RouteServiceImpl();
        HttpSession session = request.getSession();
        if(cid!=null){
            session.removeAttribute( "ssmap" );
        }
        PageBean pageBean=null;
        if(currentPage == null){
            currentPage = "1";
        }


        //收藏排行榜排名
        int i = (Integer.parseInt(currentPage) - 1) * 8;




        if( routename !=null && routeprice1 !=null && routeprice2 !=null){
            if(routename !="" || routeprice1 !="" || routeprice2 !=""){
                HashMap<String, String> map = new HashMap<>();
                map.put( "routename" ,routename);
                map.put( "routeprice1" ,routeprice1);
                map.put( "routeprice2" ,routeprice2);
                session.setAttribute( "ssmap",map );
                pageBean = service.selectlike( 8,Integer.parseInt(currentPage),map);
            }else {
                pageBean = service.selectCount( 8, Integer.parseInt(currentPage) );
            }
        }else {
            pageBean = service.selectCount( 8, Integer.parseInt(currentPage) );
        }

        Map ssmap = (Map) session.getAttribute( "ssmap" );
        if(ssmap!=null){
            pageBean = service.selectlike( 8,Integer.parseInt(currentPage),ssmap);
        }



        session.setAttribute("pb", pageBean);
        session.setAttribute( "xuhao",i );
        response.sendRedirect( "favoriterank.jsp" );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost( request,response );
    }
}
