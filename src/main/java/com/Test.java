package com;

import com.lp.entity.Category;
import com.lp.service.Impl.CategoryServiceImpl;
import com.lp.untils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        CategoryServiceImpl service = new CategoryServiceImpl();
        List<Category> categoryList = service.selectAll();
        Jedis jedis = JedisUtil.getJedis();
        jedis.set( "name","coco" );
        jedis.close();
        System.out.println(categoryList);
    }
}
