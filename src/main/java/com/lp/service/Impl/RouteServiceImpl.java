package com.lp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.dao.CategoryDao;
import com.lp.dao.RouteDao;
import com.lp.entity.*;
import com.lp.service.RouteService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class RouteServiceImpl implements RouteService {
    private InputStream in = null;
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
    public List<Route> selectAll() {
        init();
         sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );
        List<Route> list = routeDao.selectAll();
        end();
        return list;
    }

    @Override
    public PageBean selectAll(int cid, int rows, int currentPage) {


        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );

        PageHelper.startPage( currentPage,rows );
        List<Route> routeList = routeDao.selectAllcid( cid );
        PageInfo<Route> pageInfo = new PageInfo<>( routeList );
        int count = (int)pageInfo.getTotal();

        end();
        //总页数
        int ceil = (int) Math.ceil( count / (rows * 1.0) );
        PageBean pageBean = new PageBean(count,ceil,rows,currentPage,routeList);
        return pageBean;
    }

    @Override
    public PageBean selectCount(int rows, int currentPage) {

        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );
        PageHelper.startPage( currentPage,rows );
        List<Route> list = routeDao.selectCountsc();
        PageInfo<Route> pageInfo = new PageInfo<>( list );
        int count = (int)pageInfo.getTotal();
        end();

        //总页数
        int ceil = (int) Math.ceil( count / (rows * 1.0) );
        PageBean pageBean = new PageBean(count,ceil,rows,currentPage,list);
        return pageBean;
    }

    //排行榜模糊查询
    @Override
    public PageBean selectlike(int rows, int currentPage, Map<String, String> map) {
        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );

        System.out.println(map);


        QueryVo queryVo = new QueryVo();
        if( map.get( "routename" ) != "" && map.get( "routename" ) != null){
            queryVo.setRoutename("%"+ map.get( "routename" )+"%" );
        }
        if(map.get( "routeprice1" ) != ""){
            queryVo.setRouteprice1( Integer.parseInt( map.get( "routeprice1" ) ) );
        }
        if(map.get( "routeprice2" ) != ""){
            queryVo.setRouteprice2( Integer.parseInt( map.get( "routeprice2" ) ) );
        }

        PageHelper.startPage( currentPage,rows );
        List<Route> list = routeDao.selectlike( queryVo );

        System.out.println(list);

        PageInfo<Route> pageInfo = new PageInfo<>( list );
        int count = (int)pageInfo.getTotal();
        end();
        //总页数
        int ceil = (int) Math.ceil( count / (rows * 1.0) );
        return new PageBean(count,ceil,rows,currentPage,list);
    }
//    @Override
//    public int selectCount(int cid) {
//        return dao.selectCount( cid );
//    }

    //查看详情
    @Override
    public Route seeRoute(int rid) {
        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );
        Route route = routeDao.selectOne( rid );
        List<RouteImg> routeImgs = routeDao.selectSome( rid );

        //获取该条旅游的商家
        Seller seller = routeDao.selectSellOne( route.getSid() );
        end();

        init();
        sqlSession = factory.openSession();
        CategoryDao categoryDao = sqlSession.getMapper( CategoryDao.class );
        //获取旅游类型
        Category category = categoryDao.selectOne( route.getCid() );
//        Category category = this.categoryDao.selectOne( route.getCid() );
        end();
        route.setRouteImgs( routeImgs );
        route.setCategory( category );
        route.setSeller( seller );
        return route;
    }

    @Override
    public PageBean selectSou(int rows, int currentPage, Map<String, String> map) {

        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );
        QueryVo queryVo = new QueryVo();
        if( map.get( "routename" ) != "" && map.get( "routename" ) != null){
            queryVo.setRoutename("%"+ map.get( "routename" )+"%" );
        }
          PageHelper.startPage( currentPage,rows );
        List<Route> list = routeDao.selectSou( queryVo );
        PageInfo<Route> pageInfo = new PageInfo<>( list );
        int count = (int)pageInfo.getTotal();
//        List<Route> list = dao.selectSou( rows, currentPage, map );
//        int count = dao.selectSouCount( rows, currentPage, map );
        //总页数
        int ceil = (int) Math.ceil( count / (rows * 1.0) );
        return new PageBean(count,ceil,rows,currentPage,list);
    }

    @Override
    public List<Route> selectDate() {
        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );
        List<Route> list = routeDao.selectDate();
        end();
        return list;
    }

    @Override
    public List<Route> selectIsTh() {
        init();
        sqlSession = factory.openSession();
        RouteDao routeDao = sqlSession.getMapper( RouteDao.class );
        List<Route> list = routeDao.selectIsTh();
        end();
        return list;
    }
}
