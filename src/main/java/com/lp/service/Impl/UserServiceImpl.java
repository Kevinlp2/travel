package com.lp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.dao.RouteDao;
import com.lp.dao.UserDao;
import com.lp.entity.Favorite;
import com.lp.entity.PageBean;
import com.lp.entity.Route;
import com.lp.entity.User;
import com.lp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
public class UserServiceImpl implements UserService {
    private  InputStream in = null;
    private SqlSessionFactoryBuilder builder=null;
    private SqlSessionFactory factory=null;
    private SqlSession sqlSession=null;
    public void init(){

        try {
            in = Resources.getResourceAsStream( "SqlMapConfig.xml" );
        } catch (IOException e) {
            e.printStackTrace();
        }
         builder = new SqlSessionFactoryBuilder();
         factory = builder.build( in );
    }
    public void end(){

        sqlSession.commit();
        sqlSession.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int addUser(User user) {
        init();
        sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        int i = userDao.addUser( user );
        System.out.println(i);
        end();
        return i;
    }

    @Override
    public boolean userRechecking(String username) {
        init();
        sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        List<User> userList = userDao.selectAll();

        for (User u:userList) {
            if(u.getUsername().equals( username )){
                return true;
            }
        }
        end();
        return false;
    }

    @Override
    public Map<String, String> login(String username, String password) {
        HashMap<String,String> map = new HashMap<>();
        init();
        sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        List<User> userList = userDao.selectAll();
        for (User u:userList) {
            if(u.getUsername().equals( username )){
                if(u.getPassword().equals( password )){
                    if(u.getStatus().equals( "N" )){
                        map.put( "2","该账户未验证" );
                    }else {
                       map.put( "1", String.valueOf( u.getUid() ) );
                    }

                }else {
                    map.put( "0","密码错误" );
                }
            }
        }
        return map;
    }

    @Override
    public User selectOne(String username) {
        init();
        sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        List<User> userList = userDao.selectAll();
        for (User u:userList) {
            if(u.getUsername().equals( username)){
                return  u;
            }
        }
        return null;
    }

    @Override
    public boolean addfavor(int userid, int routeid) {
        init();
        sqlSession = factory.openSession(true);
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss " );
        String format1 = format.format( date );
        Favorite favorite = new Favorite( routeid, format1, userid );



        boolean b = userDao.addfavor( favorite );
        if(b){

            int i1 = userDao.updateRouteOne( routeid );

            if(i1==1){
                return true;
            }
        }
        end();
        return false;
    }

    @Override
    public boolean deletefavor(int userid, int routeid) {
        init();
        sqlSession = factory.openSession(true);
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        Favorite favorite = new Favorite();
        favorite.setRid( routeid );
        favorite.setUid( userid );
        int i = userDao.deletefavor( favorite );

        if(i==1){
            int i1 = userDao.deleteupdateRouteOne( routeid );
            if(i1==1){
                return  true;
            }
        }
        end();
        return false;
    }

    @Override
    public List<Favorite> selectFavo(int uid) {
        init();
        sqlSession = factory.openSession(true);
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        List<Favorite> list = userDao.selectFavo( uid );

        return list;
    }

    @Override
    public PageBean selectUserRoute(int rows, int currentPage, int uid) {

        init();
        sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper( UserDao.class );

        PageHelper.startPage(currentPage,rows );
        List<Favorite> list = userDao.selectFavolimit( uid );
        PageInfo<Favorite> pageInfo = new PageInfo<>( list );
        int count = (int) pageInfo.getTotal();
        end();
        ArrayList<Route> routeList = new ArrayList<>();

        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );
//        RouteDao routeDao = new RouteDaoImpl();
        for (Favorite f:list) {
            Route route = routeDao.selectOne( f.getRid() );
            routeList.add( route );
        }
        //总页数
        int ceil = (int) Math.ceil( count / (rows * 1.0) );
        return new PageBean(count,ceil,rows,currentPage,routeList);
    }

    @Override
    public boolean emailCheck(String code) {
        init();
        sqlSession = factory.openSession(true);
        UserDao userDao = sqlSession.getMapper( UserDao.class );
        return userDao.emailCheck( code );
    }
}
