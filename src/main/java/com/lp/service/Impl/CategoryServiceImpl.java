package com.lp.service.Impl;

import com.lp.dao.CategoryDao;
import com.lp.entity.Category;
import com.lp.service.CategoryService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> selectAll() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream( "SqlMapConfig.xml" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build( in );
        SqlSession sqlSession = factory.openSession();
        CategoryDao categoryDao = sqlSession.getMapper( CategoryDao.class );

        return categoryDao.selectAll();
    }
}
