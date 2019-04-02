<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>小码旅游网</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<!--引入头部-->
    <div id="header"></div>
    <!-- banner start-->
    <section id="banner">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="2000">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox" style="position: relative;left: 200px">
                <div class="item active">
                    <img src="images/banner_1.jpg" alt="">
                </div>
                <div class="item">
                    <img src="images/banner_2.jpg" alt="">
                </div>
                <div class="item">
                    <img src="images/banner_3.jpg" alt="">
                </div>
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
        </div>
    </section>
    <!-- banner end-->
    <!-- 旅游 start-->
    <section id="content">
         <!-- 小码精选start-->
        <section class="hemai_jx">
            <div class="jx_top">
                <div class="jx_tit">
                    <img src="images/icon_5.jpg" alt="">
                    <span>小码精选</span>
                </div>
                <!-- Nav tabs -->
                <ul class="jx_tabs" role="tablist">
                    <li role="presentation" class="active">
                        <span></span>
                        <a href="#popularity" aria-controls="popularity" role="tab" data-toggle="tab">人气旅游</a>
                    </li>
                    <li role="presentation">
                        <span></span>
                        <a href="#newest" aria-controls="newest" role="tab" data-toggle="tab">最新旅游</a>
                    </li>
                    <li role="presentation">
                        <span></span>
                        <a href="#theme" aria-controls="theme" role="tab" data-toggle="tab">主题旅游</a>
                    </li>
                </ul>
            </div>
            <div class="jx_content">
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="popularity">
                        <div class="row">
                            <c:forEach items="${pab1.list}" var="pab1">
                                <div class="col-md-3">
                                    <a href="/seeServlet?rid=${pab1.rid}">
                                        <img src="${pab1.rimage}" alt="">
                                        <div class="has_border">
                                            <h3>${pab1.routeIntroduce}</h3>
                                            <div class="price">网付价<em>￥</em><strong>${pab1.price}</strong><em>起</em></div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="newest">
                        <div class="row">
                            <c:forEach items="${pab2}" var="pab2">
                                <div class="col-md-3">
                                    <a href="/seeServlet?rid=${pab2.rid}">
                                        <img src="${pab2.rimage}" alt="">
                                        <div class="has_border">
                                            <h3>${pab2.routeIntroduce}</h3>
                                            <div class="price">网付价<em>￥</em><strong>${pab2.price}</strong><em>起</em></div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="theme" >
                        <div class="row">
                            <c:forEach items="${pab3}" var="pab3">
                                <div class="col-md-3">
                                    <a href="/seeServlet?rid=${pab3.rid}">
                                        <img src="${pab3.rimage}" alt="">
                                        <div class="has_border">
                                            <h3>${pab3.routeIntroduce}</h3>
                                            <div class="price">网付价<em>￥</em><strong>${pab3.price}</strong><em>起</em></div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 小码精选end-->
        <!-- 国内游 start-->
        <section class="hemai_jx">
            <div class="jx_top">
                <div class="jx_tit">
                    <img src="images/icon_6.jpg" alt="">
                    <span>国内游</span>
                </div>
            </div>
            <div class="heima_gn">
                <div class="guonei_l">
                    <img src="images/guonei_1.jpg" alt="">
                </div>
                <div class="guone_r">
                    <div class="row">
                        <c:forEach items="${pab4.list}" var="pab4">
                            <div class="col-md-4" style="height: 239px;">
                                <a href="/seeServlet?rid=${pab4.rid}">
                                    <img src="${pab4.rimage}" alt="">
                                    <div class="has_border">
                                        <h3>${pab4.routeIntroduce}</h3>
                                        <div class="price">网付价<em>￥</em><strong>${pab4.price}</strong><em>起</em></div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <!-- 国内游 end-->
        <!-- 境外游 start-->
        <section class="hemai_jx">
            <div class="jx_top">
                <div class="jx_tit">
                    <img src="images/icon_7.jpg" alt="">
                    <span>境外游</span>
                </div>
            </div>
            <div class="heima_gn">
                <div class="guonei_l">
                    <img src="images/jiangwai_1.jpg" alt="">
                </div>
                <div class="guone_r">
                    <div class="row">
                        <c:forEach items="${pab5.list}" var="pab5">
                            <div class="col-md-4">
                                <a href="/seeServlet?rid=${pab5.rid}">
                                    <img src="${pab5.rimage}" alt="">
                                    <div class="has_border">
                                        <h3>${pab5.routeIntroduce}</h3>
                                        <div class="price">网付价<em>￥</em><strong>${pab5.price}</strong><em>起</em></div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <!-- 境外游 end-->
    </section>
    <!-- 旅游 end-->
   <!--导入底部-->
    <div id="footer"></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-3.3.1.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <!--导入布局js，共享header和footer-->
    <script type="text/javascript" src="js/include.js"></script>
</body>
</html>