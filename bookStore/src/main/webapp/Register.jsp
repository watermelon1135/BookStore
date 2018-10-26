<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${1==1}">
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
	<title>注册界面</title>
</head>
<body>
<div id="top">
	<div style="width: 20%;float: left">&nbsp;</div>
	<div style="width: 50%;float: left;font-style: italic">welcome to mysterious shopstore</div>

	<div style="float: left;font-style: italic">
		<span id="showTime"></span>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">ENGLISH</a></div>
</div>
<div style="height: 40px"> </div>
<div style="background-image: url(/images/background2.jpg);background-size:100%" >
	<div style="height: 200px"> </div>
	<div align="center" style="width: 40%;margin-left: 30%;border: 1px solid ">
		<h3 style="font-family: 微软雅黑;font-size: 20px">开始注册</h3>
		<span style="color: red"> ${errorMsg}</span>
		<form action="/register" method="POST">
			<input type="hidden" name="type" value="1">
			<div class="height">用户名：<input type="text" name="username" placeholder="请输入用户名" class="text"></div>
			<div class="height">密码：<input type="password" name="password" placeholder="请输入密码" class="text"></div>
			<div class="height">确认密码：<input type="password" name="confirmPassword" placeholder="请确认密码" class="text"></div>
			<div class="height" style="padding-left: 90px"><input type="text" placeholder="请输入验证码" name="inputCode" style="width: 100px;height: 25px"></div>
			<img id="img" src="/code" align="center" onclick="javascript:this.src='randomcode.jpg?tm='+Math.random()"/>
			<span id="update" onclick="javascript:img.src='/code?tm='+Math.random()"><a href="###">看不清楚?换一张</a></span>
			<div class="height"><input type="submit" value="注册" style="background-color: #ff1877" class="text"></div>
		</form>
			<a href="/Login.jsp"  style="padding-left:115px"><button>返回登录</button></a>
	</div>
	<div style="height: 150px"></div>
</div>
</body>
</html>
</c:if>