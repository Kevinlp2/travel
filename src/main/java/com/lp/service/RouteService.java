package com.lp.service;

import com.lp.entity.PageBean;
import com.lp.entity.Route;

import java.util.List;
import java.util.Map;

public interface RouteService {
    List<Route> selectAll();
    PageBean selectAll(int cid, int rows, int currentPage);
    //排行榜分页
    PageBean selectCount(int rows, int currentPage);
    //排行榜模糊查询
    PageBean selectlike(int rows, int currentPage, Map<String,String> map);
//    int selectCount(int cid);
    Route seeRoute(int rid);

    //搜索路线
    PageBean selectSou(int rows, int currentPage, Map<String,String> map);

    //最新旅游
    List<Route> selectDate();
    //主题旅游
    List<Route> selectIsTh();
}
