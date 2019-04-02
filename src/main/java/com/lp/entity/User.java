package com.lp.entity;

import lombok.*;

/**
 * tab_user表
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int uid;
    private String username;
    private  String password;
    private  String name;
    private String birthday;
    private String sex;
    private String telephone;
    private String email;
    private String status;
    private String code;

}
