package com.lp.service;

import com.lp.entity.Favorite;
import com.lp.entity.PageBean;
import com.lp.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int addUser(User user);
    boolean userRechecking(String username);
    Map<String,String> login(String username, String password);
    User selectOne(String username);
    //用户添加收藏
    boolean addfavor(int userid,int routeid);
    //用户取消收藏
    boolean deletefavor(int userid,int routeid);
    //搜索该用户的收藏
    List<Favorite> selectFavo(int uid);
    //搜索出该用户收藏的路线的详细信息
    PageBean selectUserRoute(int rows,int currentPage,int uid);

    //邮箱验证
    boolean emailCheck(String code);
}
