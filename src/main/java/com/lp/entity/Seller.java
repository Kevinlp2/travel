package com.lp.entity;

import lombok.*;

/**
 * 商家表
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seller {
    private int sid;
    private String sname;
    private String consphone;
    private String address;
}
