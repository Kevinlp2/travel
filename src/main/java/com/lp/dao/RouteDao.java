package com.lp.dao;

import com.lp.entity.QueryVo;
import com.lp.entity.Route;
import com.lp.entity.RouteImg;
import com.lp.entity.Seller;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RouteDao {
    List<Route> selectAll();
    List<Route> selectAllcid(int cid);

    Route selectOne(int rid);

    List<RouteImg> selectSome(int rid);
    //根据sid查询商家
    Seller selectSellOne(int sid);

    //收藏排行榜
    List<Route> selectCountsc();


    //收藏排行耪模糊查询
    List<Route> selectlike(QueryVo vo);


    //路线搜索
    List<Route> selectSou(QueryVo vo);


    //最新旅游
    List<Route> selectDate();
    //主题旅游
    List<Route> selectIsTh();

}
