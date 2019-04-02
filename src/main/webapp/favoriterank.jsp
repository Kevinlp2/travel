<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>收藏排行榜</title>
        <link rel="stylesheet" type="text/css" href="css/common.css">
        <link rel="stylesheet" type="text/css" href="css/ranking-list.css">

        <style>
            .contant .page_num_inf {
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
            .contant .page_num_inf span {
                color: #ff4848;
            }
        </style>
    </head>
    <body>
    <!--引入头部-->
    <div id="header"></div>
        <div class="contant">
            <div class="shaixuan">
                <span>线路名称</span>
                <input type="text" id="routername">
                <span>金额</span>
                <input type="text" id="routeprice1">~<input type="text" id="routeprice2">
                <button onclick="shousuo()">搜索</button>
            </div>
            <div class="list clearfix">
                <ul>
                    <c:forEach items="${pb.list}" var="li" varStatus="status">
                        <li>
                            <span class="num one" style="color: white">${status.index+1+xuhao}</span>
                            <a href="/seeServlet?rid=${li.rid}"><img src="${li.rimage}" alt=""></a>
                            <h4><a href="/seeServlet?rid=${li.rid}">${li.rname}</a></h4>
                            <p>
                                <b class="price">&yen;<span>${li.price}</span>起</b>
                                <span class="shouchang">已收藏${li.count}次</span>
                            </p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="page_num_inf">
                <i></i> 共<span>${pb.totalPage}</span>页<span>${pb.totalCount}</span>条&nbsp&nbsp&nbsp当前是第<span>${pb.currentPage}</span>页
            </div>
            <div class="container">
                <ul id="page"></ul>
            </div>
        </div>
    	
         <!--导入底部-->
   		 <div id="footer"></div>
    <!--导入布局js，共享header和footer-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-paginator.js"></script>
    <script type="text/javascript" src="js/include.js"></script>
    <script>
        $(function () {
            var currentPage = ${pb.currentPage};
            var totalPages = ${pb.totalPage};
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
                    window.location.href="/favorServlet?currentPage="+page;
                }
            });
        });

        function shousuo() {
            window.location.href="/favorServlet?routername="+$("#routername").val()+"&routeprice1="+$("#routeprice1").val()+"&routeprice2="+$("#routeprice2").val();
        }
    </script>
    </body>
</html>