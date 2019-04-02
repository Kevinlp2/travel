package com.lp.entity;

import lombok.*;


/**
 * 用户收藏表
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Favorite {
    private int rid;
    private String date;
    private  int uid;
}
