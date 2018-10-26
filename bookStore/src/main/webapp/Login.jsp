<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		#top{
			background-color:#4f8d05 ;
			color :#ffffff;
			height:50px;
			text-align: left ;
			line-height: 50px;
		}
		.text{
			width: 190px;
			height: 25px;
		}
		.height{
			height: 55px;
		}
	</style>
	<script src="../js/showTime.js"></script>
	<title>登录界面</title>
</head>
<body >

<div id="top">
	<div style="width: 20%;float: left">&nbsp;</div>
	<div style="width: 50%;float: left;font-style: italic">welcome to mysterious shopstore</div>

	<div style="float: left;font-style: italic">
		<span id="showTime"></span>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">ENGLISH</a></div>
</div>
<div style="height: 40px"> </div>


<div style="background-image: url(/images/background.jpg)">
	<div><img src="/images/logo.png" border="1px solid #4f8d05 "></div>
	<div style="height: 80px"></div>


	<div style="padding-left: 1265px">
		<div style="width:300px;height:300px;">
			<span style="color: red;padding-right:30px;font-weight:bolder;font-size:25px"> ${errorMsg}</span>

			<form method="POST" action="/login">
				<h3 style="font-family: 微软雅黑;font-size : 18px;padding-left: 48px">用户登陆</h3>
				<div class="height"><input type="text" name="username" placeholder="请输入用户名" class="text"></div>
				<div class="height"><input type="password" name="password" placeholder="请输入密码" class="text"></div>
				<div style="padding-left: 93px;" class="height">
					<select name="userType" style="width: 100px;height: 25px" >
						<option value="manager" >管理员</option>
						<option value="customer" selected="selected">顾客</option>
					</select>
				</div>

				<div class="height"><input type="submit" value="登录" style="background-color: #ff1877" class="text"></div>
				<a href="/Register.jsp"  style="padding-left:115px">新用户注册</a>

			</form>
		</div>
	</div>
</div>
</body>
</html>