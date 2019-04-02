package com.lp.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageBean {
    private int totalCount;//总条数  ---------数据库
    private int totalPage;//总页数    ----------算出来的
    private int rows;//每页显示行数 --------------自定义
    private int currentPage;//当前页码    ---------前端
    private List<Route> list; // 内容---------数据库
}
