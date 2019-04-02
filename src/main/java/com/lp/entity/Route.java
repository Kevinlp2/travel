package com.lp.entity;

import lombok.*;

import java.util.List;

/**
 * 旅游线路表
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Route {
    private int rid;
    private String rname;
    private double price;
    private String routeIntroduce;
    private char rflag;
    private String rdate;
    private char isThemTour;
    private  int count;
    private  int cid;
    private String rimage;
    private int sid;
    private  int sourceid;
    private Category category;
    private Seller seller;
    private List<RouteImg> routeImgs;
    private List<Favorite> favoriteList;

    public Route(int rid, String rname, double price, String routeIntroduce, char rflag, String rdate, char isThemTour, int count, int cid, String rimage, int sid, int sourceid) {
        this.rid = rid;
        this.rname = rname;
        this.price = price;
        this.routeIntroduce = routeIntroduce;
        this.rflag = rflag;
        this.rdate = rdate;
        this.isThemTour = isThemTour;
        this.count = count;
        this.cid = cid;
        this.rimage = rimage;
        this.sid = sid;
        this.sourceid = sourceid;
    }
}
