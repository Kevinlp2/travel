<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>注册</title>
        <link rel="stylesheet" type="text/css" href="css/common.css">
        <link rel="stylesheet" href="css/register.css">
		<!--导入jquery-->
		<script src="js/jquery-3.3.1.js"></script>
		<style type="text/css">
			#registerForm .td_right>.errorss{
				border: 2px solid red;
			}
		</style>
    </head>
	<body>
	<!--引入头部-->
	<div id="header"></div>
        <!-- 头部 end -->
    	<div class="rg_layout">
    		<div class="rg_form clearfix">
    			<div class="rg_form_left">
    				<p>新用户注册</p>
    				<p>USER REGISTER</p>
    			</div>
    			<div class="rg_form_center">
					
					<!--注册表单-->
    				<form id="registerForm" >
						<!--提交处理请求的标识符-->
						<input type="hidden" name="action" value="register">
    					<table style="margin-top: 25px;">
    						<tr>
    							<td class="td_left">
    								<label for="username">用户名</label>
    							</td>
    							<td class="td_right">
    								<input type="text"   id="username" name="username" placeholder="请输入账号" onblur="yzyhm()">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="password">密码</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="password" name="password" placeholder="请输入密码" onblur="yzpasw()">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="email">Email</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="email" name="email" placeholder="请输入Email" onblur="yzemail()">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="name">姓名</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="name" name="name" placeholder="请输入真实姓名" >
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="telephone">手机号</label>
    							</td>
    							<td class="td_right">
    								<input type="text" id="telephone" name="telephone" placeholder="请输入您的手机号">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="sex">性别</label>
    							</td>
    							<td class="td_right gender">
    								<input type="radio" id="sex" name="sex" value="男" checked> 男
    								<input type="radio" name="sex" value="女"> 女
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="birthday">出生日期</label>
    							</td>
    							<td class="td_right">
    								<input type="date" id="birthday" name="birthday" placeholder="年/月/日" >
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left">
    								<label for="check">验证码</label>
    							</td>
    							<td class="td_right check">
    								<input type="text" id="check" name="check" class="check" onblur="yzyzm()">
    								<img id="vcode" src="/checkCodeServlet" height="32px" alt="" onclick="refreshCode()">
    							</td>
    						</tr>
    						<tr>
    							<td class="td_left"> 
    							</td>
    							<td class="td_right check"> 
    								<input type="button" class="submit" onclick="regsubmit()" value="注册">
									<span id="msg" style="color: red;"></span>
    							</td>
    						</tr>
    					</table>
    				</form>
    			</div>
    			<div class="rg_form_right">
    				<p>
    					已有账号？
    					<a href="login.jsp">立即登录</a>
    				</p>
    			</div>
    		</div>
    	</div>
        <!--引入尾部-->
    	<div id="footer"></div>
		<!--导入布局js，共享header和footer-->
		<script type="text/javascript" src="js/include.js"></script>
    	<script>
			//验证码
            function refreshCode() {
                var element = document.getElementById("vcode");
                element.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
            }

            //验证用户名
            function yzyhm() {
                var $username = $("#username");
                if(typeof $username.val() == "undefined" || $username.val() == null || $username.val() == ""){
                    $username.addClass("errorss");
                    $username.attr("placeholder","不能为空");
                }else {
					userRechecking($username.val());
                }
                return false;
            }

            //验证用户名是否已存在
			function userRechecking(obj) {
				$.ajax({
					url:"/user/userRechecking",
					type:"post",
					data:"username="+obj,
					dataType:"json",
					success:function (data) {
						console.log(data);
						if(data.flag){

                            $("#username").addClass("errorss");
                            $("#msg").html("该用户名已被占用");
                            return false;
						}else {
                            $("#username").removeClass("errorss");
                            $("#msg").html("");
                            return true;
						}
                    }
				})
            }
			//验证密码
            function yzpasw() {
                var $password = $("#password");
                if(typeof $password.val() == "undefined" || $password.val() == null || $password.val() == ""){
                    $password.addClass("errorss");
                    $password.attr("placeholder","不能为空");
                }else {
                    $password.removeClass("errorss");
                    $password.attr("placeholder","");
                    return true;
                }
                return false;
            }
            //验证邮箱
            function yzemail() {
                var $email = $("#email");
                if(typeof $email.val() == "undefined" || $email.val() == null || $email.val() == ""){
                    $email.addClass("errorss");
                    $email.attr("placeholder","不能为空");
                }else {
                    $email.removeClass("errorss");
                    $email.attr("placeholder","");
                    return true;
                }

                return false;
            }
            //注册验证
			function yzyzm() {
                var $check = $("#check");
                if(typeof $check.val() == "undefined" || $check.val() == null || $check.val() == ""){
                    $check.addClass("errorss");
                    $check.attr("placeholder","不能为空");
                }else {
                    $check.removeClass("errorss");
                    $check.attr("placeholder","");
                    return true;
                }
                return false;
            }

            //注册提交验证
			function regsubmit() {

				if (yzpasw() && yzemail() && yzyzm()) {
					$.ajax({
						url: "/user/register",
						type: "post",
						data: $("#registerForm").serialize(),
						dataType: "json",
						success: function (data) {
							console.log(data);
							if (data.flag) {
								window.location.href = "register_ok.jsp";
							} else {
								$("#msg").html(data.msg);
							}
							//更改验证码
							refreshCode();
						}
					})
				}
            }
		</script>
    </body>
</html>