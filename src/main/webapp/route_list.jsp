<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>小码旅游-搜索</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-paginator.js"></script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<div class="page_one">
    <div class="contant">
        <div class="crumbs">
            <img src="images/search.png" alt="">
            <p>小码旅行><span>搜索结果</span> >${sousuo} </p>
        </div>
        <div class="xinxi clearfix" style="width: 1200px;">
            <div class="left">
                <div class="header">
                    <span>商品信息</span>
                    <span class="jg">价格</span>
                </div>
                <ul>
                    <c:forEach items="${pb.list}" var="list">
                        <li>
                            <div class="img"><img src="${list.rimage}" alt="" style="width: 300px;"></div>
                            <div class="text1">
                                <p>${list.rname}</p>
                                <br/>
                                <p>${list.routeIntroduce}</p>
                            </div>
                            <div class="price">
                                <p class="price_num">
                                    <span>&yen;</span>
                                    <span>${list.price}</span>
                                    <span>起</span>
                                </p>
                                <p><a href="/seeServlet?rid=${list.rid}">查看详情</a></p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div class="page_num_inf">
                    <i></i>
                    共<span>${pb.totalPage}</span>页<span>${pb.totalCount}</span>条&nbsp&nbsp&nbsp当前是第<span>${pb.currentPage}</span>页
                </div>
                <div class="container">
                    <ul id="page"></ul>
                </div>
            </div>
            <div class="right" style="width: 280px;">
                <div class="top">
                    <div class="hot">HOT</div>
                    <span>热门推荐</span>
                </div>
                <ul>
                    <c:forEach items="${pb2.list}" var="pb2">
                        <li>
                            <a href="/seeServlet?rid=${pb2.rid}">
                                <div class="left"><img src="${pb2.rimage}" alt=""></div>
                                <div class="right">
                                    <p>${pb2.rname}</p>
                                    <p>网付价<span>&yen;<span>${pb2.price}</span>起</span>
                                    </p>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<!--引入头部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
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
                window.location.href="/routeServlet?currentPage="+page;
            }
        });
    });
</script>
</body>

</html>