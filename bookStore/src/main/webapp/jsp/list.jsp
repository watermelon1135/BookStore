<%@ page import="java.util.List" %>
<%@ page import="com.nchu.bookstore.domain.CartItem" %>
<%@ page import="com.nchu.bookstore.domain.ShoppingCart" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
			background-color:#4f8d05;
			color :#ffffff;
			height:50px;
			text-align: left ;
			line-height: 50px;
		}
		.text{
			width: 200px;
			height: 34px;
		}
		.logo{
			font-size: 30px;
			color: #2e312a;
			font-style: italic;
		}
		.a{
			text-decoration: none;
		}
		.search{
			background-image: url(/images/search.png);
			width: 40px;
			height: 34px;
			margin-top:5px;
		}
	</style>
	<title>商品列表</title>
	<script src="../js/showTime.js"></script>
</head>
<body onload="run()">


<div id="top">
	<div style="width: 10%;float: left">&nbsp;</div>
	<div style="width: 50%;float: left;font-style: italic">welcome to mysterious shopstore
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span id="showTime" style="margin-left:0px;margin-top:6px"></span>
		</div>

	<div style="float: left;font-style: italic">

		<span style="padding-left: 60px">当前管理员为：${USER_IN_SESSION.username}</span>
		<span style="padding-left: 30px"><a href="/cancel" class="a">注销</a></span>
	</div>
</div>

<script>
    var banners = ["/images/discount.jpg","/images/discount2.jpg","/images/discount3.jpg","/images/discount4.jpg"]; // 图片地址
    var counter = 0;
    function run(){
        setInterval(cycle,3000);  //重复运行cycle函数，周期3000ms
    }
    function cycle(){
        counter++;
        if(counter == banners.length)
            counter = 0;
        document.getElementById("banner").src = banners[counter];
    }
    function Go(pageNo) {
        document.getElementById("currentPage").value = pageNo;
        document.forms[0].submit();
    }
</script>


<div style="height: 100px;background-color: #eaefe1;line-height: 30px">
	<span class="logo" style="padding-left: 580px">mysterious&nbsp;&nbsp;bookstore</span>
	<span style="font-style: italic;font-size: 20px;padding-left: 380px">
		<a href="/manage/books?cmd=edit" class="a" style="padding-top:20px;display:inline-block">
			<span style="color: #fd6804;font-size: 40px">添加</span>
			<span style="color: #fd6804;font-size: 60px">+
		</span>
		</a>
	</span>
</div>

<br>
	<form action="/manage/books">
	<div style="float:right;">
		<input type="text" name="keyword" placeholder="图书查询" class="text" style="background-color: #fafbf8;" value="${qo.keyword}">
		<input type="button" class="search" value="" onclick="Go()"></span>
		<br><br>
	</div>
	<br><br>


<a href="#c4"><img id="banner" src="/images/discount.jpg" style="width: 1200px;height: 500px;padding-left:150px"></a>


<div style="height: 60px"></div>
<%--<a href="#c4"><img src="/images/discount.jpg" style="width: 1200px;height: 500px;padding-left: 150px;"></a>--%>


<div style="padding-left: 160px;padding-top: 50px" >
	<a name="c4"></a>
	<c:forEach items="${pageResult.listData}" var="b" varStatus="vs">

		<div style='width: 25%;height:430px;float: left'>
			<div><a href="/showBooks?id=${b.id}"><img src="/images/${b.book_image}" alt="加载失败" style="height: 230px;width: 180px"></a></div>
			<div style="height: 35px"></div>
			<div style="height: 25px"><a href="/showBooks?id=${b.id}" class="a">${b.book_name}</a></div>
			<div style="height:25px">
				<span style="font-size: 18px;font-style: italic;color: #c60f04">${b.book_price}</span>
				<span><a href="/manage/books?cmd=delete&id=${b.id}" class="a" style="padding-left: 25px">下架</a></span>
				<span><a href="/manage/books?cmd=edit&id=${b.id}" class="a" style="padding-left: 25px">编辑</a></span>
			</div>
		</div>

	</c:forEach>
	<div style="clear:left;margin-left:200px;font-size:20px;height:200px">
		<a href="javascript:Go(1)">首页</a>
		<a href="javascript:Go(${pageResult.pervPage})">上页</a>
		<a href="javascript:Go(${pageResult.nextPage})">下页</a>
		<a href="javascript:Go(${pageResult.totalPage})">末页</a>
		当前第${pageResult.currentPage}/${pageResult.totalPage}页
		一共有${pageResult.totalCount}条数据&nbsp;&nbsp;&nbsp;
		跳转到<input type="number" name="currentPage" id="currentPage" value="${pageResult.currentPage}" min="1" max="${pageResult.totalPage}">页
		<input type="button" onclick="Go()" value="Go">&nbsp;&nbsp;
		每页
		<select name="pageSize" onchange="Go(${pageResult.currentPage})">
			<c:forEach items="${pageResult.pageItems}" var="item">
				<option ${pageResult.pageSize == item ? "selected":""}>${item}</option>
			</c:forEach>
		</select>
	</div>
</div>
	</form>
</body>
</html>

</c:if>