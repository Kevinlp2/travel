<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 头部 start -->
    <header id="header">
        <div class="top_banner">
            <img src="images/top_banner.jpg" alt="">
        </div>
        <div class="shortcut">
        <c:if test="${empty username}">
            <!-- 未登录状态  -->
            <div class="login_out">
                <a href="login.jsp">登录</a>
                <a href="register.jsp">注册</a>
            </div>
        </c:if>
        <c:if test="${username !=''}">
            <!-- 登录状态  -->
            <div class="login">
                <span>欢迎回来，${username}</span>
                <a href="/user/myfavorite" class="collection">我的收藏</a>
                <a href="/isLoginServlet">退出</a>
            </div>
        </c:if>

        </div>
        <div class="header_wrap">
            <div class="topbar">
                <div class="logo">
                    <a href="/"><img src="images/logo.jpg" alt=""></a>
                </div>
                <div class="search">
                    <input name="" id="routename" type="text" placeholder="请输入路线名称" class="search_input" autocomplete="off">
                    <a href="javascript:;" onclick="shousuo1()" class="search-button" style="height: 36px;">搜索</a>
                </div>
                <div class="hottel">
                    <div class="hot_pic">
                        <img src="images/hot_tel.jpg" alt="">
                    </div>
                    <div class="hot_tel">
                        <p class="hot_time">客服热线(9:00-6:00)</p>
                        <p class="hot_num">400-618-9090</p>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- 头部 end -->
     <!-- 首页导航 -->
    <div class="navitem">
        <ul class="nav">
            <li class="nav-active"><a href="index.jsp">首页</a></li>
            <c:forEach items="${categorys}" var="cate">
                <li><a href="/routeServlet?cid=${cate.cid}"> ${cate.cname} </a> </li>
            </c:forEach>
            <li><a href="/favorServlet?cid=0">收藏排行榜</a></li>
        </ul>
    </div>
    <script>
        $(function () {
            $(".navitem .nav li").each(function () {

            })
        })
        function shousuo1() {
            if($("#routename").val() != ""){
                window.location.href="/routeServlet?routename="+$("#routename").val();
            }else {
                alert("请填入搜索信息")
            }

        }
    </script>