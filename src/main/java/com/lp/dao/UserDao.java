package com.lp.dao;

import com.lp.entity.Favorite;
import com.lp.entity.User;

import java.util.List;

public interface UserDao {

    int addUser(User user);

    List<User> selectAll();

    //添加收藏
    boolean addfavor(Favorite favorite);


    //根据routeid修改该路线收藏次数
    int updateRouteOne(int routeid);
    //取消收藏
    int deletefavor(Favorite favorite);
    //根据routeid修改该路线收藏次数
    int deleteupdateRouteOne(int routeid);

    //搜索该用户的收藏
    List<Favorite> selectFavo(int uid);

    //分页搜索该用户的收藏
    List<Favorite> selectFavolimit(int uid);
    //邮箱验证
    boolean emailCheck(String code);
}
