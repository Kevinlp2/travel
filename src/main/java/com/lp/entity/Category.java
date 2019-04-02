package com.lp.entity;

import lombok.*;

/**
 * 旅游线路分类表
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    private int cid;
    private String cname;
}
