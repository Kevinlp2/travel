package com.lp.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.dao.BaseDao;
import com.lp.entity.PageBean;
import com.lp.entity.ResultInfo;
import com.lp.entity.Route;
import com.lp.entity.User;
import com.lp.service.Impl.RouteServiceImpl;
import com.lp.service.Impl.UserServiceImpl;
import com.lp.service.RouteService;
import com.lp.service.UserService;
import com.lp.untils.JedisUtil;
import com.lp.untils.MailUtils;
import com.lp.untils.Md5Util;
import com.lp.untils.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    //判断是否登陆
    public void exitLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute( "username","" );
        response.sendRedirect( "login.jsp" );
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter( "check" );
        HttpSession session = request.getSession();
        String checkcode  = (String) session.getAttribute( "checkCode_session" );
        session.removeAttribute( "checkCode_session" );
        ResultInfo info = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        if(!checkcode.equalsIgnoreCase( code )){

            info.setFlag( false );
            info.setMsg("验证码错误");

            String json = null;
            try {
                json = mapper.writeValueAsString( info );
                response.setContentType( "application/json;charset=utf-8" );
                response.getWriter().write( json );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate( user,map );
                //MD5加密
                user.setPassword( Md5Util.encodeByMd5( user.getUsername()+user.getPassword() ) );

                user.setStatus("N");
                user.setCode( UuidUtil.getUuid());
                int i = service.addUser( user );
                if(i==1){
                    info.setFlag( true );
                    info.setMsg( "注册成功" );
                    session.setAttribute( "msg","恭喜，注册成功！请登录您的注册邮箱进行激活您的账号，激活后才能登录。" );
                    MailUtils.sendMail( user.getEmail(),"账户验证:http://localhost:8080/user/emailChacked?code="+user.getCode() ,"注册验证");
                }else {
                    info.setFlag( false );
                    info.setMsg( "注册失败，请重新注册" );
                }

                String json = mapper.writeValueAsString( info );
                response.setContentType( "application/json;charset=utf-8" );
                response.getWriter().write( json );

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //邮箱验证
    public  void emailChacked(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter( "code" );
        HttpSession session = request.getSession();
        boolean b = service.emailCheck( code );
        if(b){
            session.setAttribute( "msg","恭喜，验证成功！可以登陆了。" );
            response.sendRedirect( "/login.jsp" );
            System.out.println("邮箱验证成功!");
        }
    }
    //验证用户名是否重复
    public  void  userRechecking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding( "utf-8" );
        String username = request.getParameter( "username" );
        boolean b = service.userRechecking( username );
        ResultInfo info = new ResultInfo();
        if(b){
            info.setFlag( true );
            info.setMsg( "该用户名已被占用" );
        }else {
            info.setFlag( false );
            info.setMsg( "该用户名可以注册" );
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( info );
        response.setContentType( "application/json;charset=utf-8" );
        response.getWriter().write( json );

    }

    //登陆
    public  void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding( "utf-8" );
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        String check = request.getParameter( "check" );

        String zdlogin = request.getParameter( "zdlogin" );


        if(zdlogin!=null){
            Cookie cookie = new Cookie("zdlogin","true");
            cookie.setMaxAge( 60*60 );
        }

        HttpSession session = request.getSession();
        String checkcode  = (String) session.getAttribute( "checkCode_session" );
        session.removeAttribute( "checkCode_session" );
        ResultInfo info = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        if(!checkcode.equalsIgnoreCase( check )){
            try {
                info.setFlag( false );
                info.setMsg("验证码错误");
                json = mapper.writeValueAsString( info );
                response.setContentType( "application/json;charset=utf-8" );
                response.getWriter().write( json );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            String pass = Md5Util.encodeByMd5( username+password );
            Map<String, String> stringMap = service.login( username, pass );
            Set<String> key = stringMap.keySet();
            for (String i:key) {
                if(i.equals( "0" )){
                    info.setFlag( false );
                    info.setMsg("密码错误");
                }else if(i.equals( "1" )){
                    info.setFlag( true );
                    info.setMsg("登陆成功");

                    System.out.println(zdlogin);
                    if(zdlogin != null){
                        // 说明用户想自动登录
                        Cookie cookie = new Cookie("autoLogin", username + "&" + pass);
                        // 设置cookie的存活时间和绑定路径
                        cookie.setMaxAge(360000);
//                        cookie.setPath(request.getContextPath());
                        // 在响应中添加cookie,并返回给浏览器
                        response.addCookie(cookie);
                    }
                    session.setAttribute( "username",username );

                    session.setAttribute( "userid",stringMap.get( i ));

                    RouteService service = new RouteServiceImpl();

                    //人气旅游
                    PageBean pab1 = service.selectCount( 4, 1 );
                    //最新旅游
                    List<Route> pab2 = service.selectDate();
                    //主题旅游
                    List<Route> pab3 = service.selectIsTh();
                    //国内游
                    PageBean pab4 = service.selectAll( 5, 6, 1 );
                    //境外游
                    PageBean pab5 = service.selectAll( 8, 6, 1 );

                    session.setAttribute( "pab1",pab1 );
                    session.setAttribute( "pab2",pab2 );
                    session.setAttribute( "pab3",pab3 );
                    session.setAttribute( "pab4",pab4 );
                    session.setAttribute( "pab5",pab5 );
                }else if(i.equals( "2" )){
                    info.setFlag( false );
                    info.setMsg("该账户未验证，请去邮箱进行验证");
                }else {
                    info.setFlag( false );
                    info.setMsg("该用户不存在，请先注册");
                }
            }

            json = mapper.writeValueAsString( info );
            response.setContentType( "application/json;charset=utf-8" );
            response.getWriter().write( json );
        }

    }

    //用户收藏旅游路线
    public void userfavorite(HttpServletRequest request, HttpServletResponse response) throws Exception

    {
        String userid = request.getParameter( "userid" );
        String routeid = request.getParameter( "routeid" );
        boolean b = service.addfavor( Integer.parseInt(userid), Integer.parseInt(routeid) );
        ResultInfo info = new ResultInfo();
        if(b){

            info.setFlag( true );
            info.setMsg( "收藏成功" );
        }else {
            info.setFlag( false );
            info.setMsg( "收藏失败" );
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( info );
        response.setContentType( "application/json;charset=utf-8" );
        response.getWriter().write( json );
    }
    //用户查看我的收藏
    public  void myfavorite(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute( "userid" );
        String currentPage = request.getParameter( "currentPage" );
        PageBean pageBean=null;
        if(currentPage == null ){
            currentPage = "1";
        }
        pageBean = service.selectUserRoute( 12,Integer.parseInt( currentPage ),Integer.parseInt( userid ) );
        session.setAttribute( "upb",pageBean );
        response.sendRedirect( "/myfavorite.jsp" );
    }

    //用户取消收藏
    public  void quxiaos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userid = request.getParameter( "userid" );
        String routeid = request.getParameter( "routeid" );
        boolean b = service.deletefavor( Integer.parseInt( userid ), Integer.parseInt( routeid ) );
        ResultInfo info = new ResultInfo();
        if(b){
            info.setFlag( true );
            info.setMsg( "取消收藏成功" );
        }else {
            info.setFlag( false );
            info.setMsg( "取消收藏失败" );
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( info );
        response.setContentType( "application/json;charset=utf-8" );
        response.getWriter().write( json );
    }
}
