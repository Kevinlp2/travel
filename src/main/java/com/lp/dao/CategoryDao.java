package com.lp.dao;

import com.lp.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> selectAll();
    Category selectOne(int cid);
}
