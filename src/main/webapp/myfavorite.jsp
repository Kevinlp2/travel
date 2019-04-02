<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>小码旅游网-我的收藏</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/common.css">
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery-3.3.1.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-paginator.js"></script>
       <style>
           .tab-content .row>div {
            margin-top: 16px;
           } 
           .tab-content {
            margin-bottom: 36px;
           }


             .page_num_inf {
                color: #878787;
                font-size: 19px;
                margin-bottom: 20px;
            }
           .page_num_inf i {
               float: left;
               width: 4px;
               background-color: #878787;
               height: 20px;
               margin-top: 5px;
               margin-right: 10px;
           }
            .page_num_inf span {
               color: #ff4848;
           }

       </style>
    </head>
    <body>
    <!--引入头部-->
    <div id="header"></div>
         <!-- 排行榜 start-->
        <section id="content">            
            <section class="hemai_jx">
                <div class="jx_top">
                    <div class="jx_tit">
                        <img src="images/icon_5.jpg" alt="">
                        <span>我的收藏</span>
                    </div>                    
                </div>
                <div class="jx_content">
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home">
                            <div class="row">
                                <c:forEach items="${upb.list}" var="upb">
                                    <div class="col-md-3" style="height: 261px">
                                        <a href="/seeServlet?rid=${upb.rid}">
                                            <img src="${upb.rimage}" alt="">
                                            <div class="has_border">
                                                <h3>${upb.routeIntroduce}</h3>
                                                <div class="price">网付价<em>￥</em><strong>${upb.price}</strong><em>起</em></div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>                       
                    </div>
                </div>
                <div class="page_num_inf">
                    <i></i> 共<span>${upb.totalPage}</span>页<span>${upb.totalCount}</span>条&nbsp&nbsp&nbsp当前是第<span>${upb.currentPage}</span>页
                </div>
                <div class="container">
                    <ul id="page"></ul>
                </div>
            </section>                      
        </section>
        <!-- 排行榜 end-->
    	
         <!--引入尾部-->
    	<div id="footer"></div>
        <!--导入布局js，共享header和footer-->
        <script type="text/javascript" src="js/include.js"></script>
    <script>
        $(function () {
            var currentPage = ${upb.currentPage};
            var totalPages = ${upb.totalPage};
            $("#page").bootstrapPaginator({
                bootstrapMajorVersion: 3,//bootstrap版本
                currentPage: currentPage,//当前页码
                totalPages: totalPages,//总页数（后台传过来的数据）
                numberOfPages: 10,//一页显示几个按钮
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                },//改写分页按钮字样
                onPageClicked: function (event, originalEvent, type, page) {
                    window.location.href="/user/myfavorite?currentPage="+page;
                }
            });
        });
    </script>
    </body>
</html>