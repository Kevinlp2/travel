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

@WebServlet("/routeServlet")
public class RouteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();


        String cid = request.getParameter( "cid" );
        String currentPage = request.getParameter( "currentPage" );
        //获取搜索框填入的信息
        String routename = request.getParameter( "routename" );

        RouteService service = new RouteServiceImpl();
        PageBean pageBean=null;

        if(currentPage == null ){
            currentPage = "1";
        }

        if(routename!=null && routename !=null){
            session.removeAttribute( "cid" );
            session.setAttribute( "sousuo",routename );
            HashMap<String, String> map = new HashMap<>();
            map.put( "routename",routename );
            pageBean = service.selectSou( 8,Integer.parseInt(currentPage),map);
        }
        String sousuo = (String) session.getAttribute( "sousuo" );
        if(sousuo!=null && sousuo !=""){
            session.removeAttribute( "cid" );


            HashMap<String, String> map = new HashMap<>();
            map.put( "routename",sousuo );
            pageBean = service.selectSou( 8,Integer.parseInt(currentPage),map);
        }
        String cid1 = (String) session.getAttribute( "cid" );

        if(cid!=null){
            session.removeAttribute( "sousuo" );
            pageBean = service.selectAll( Integer.parseInt(cid), 8, Integer.parseInt(currentPage) );
            session.setAttribute( "cid",cid );
        }else {
            if(cid1!=null){
                session.removeAttribute( "sousuo" );
                pageBean = service.selectAll( Integer.parseInt(cid1), 8, Integer.parseInt(currentPage) );
                session.setAttribute( "cid",cid1 );
            }
        }
        //热门推荐列表
        PageBean pb = service.selectCount( 5, 1 );
        request.getSession().setAttribute("pb", pageBean);
        request.getSession().setAttribute("pb2", pb);
        response.sendRedirect( "route_list.jsp" );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost( request,response );
    }
}
