<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<span id="showTime" style="margin-left:0px;margin-top:6px"></span></div>

	<div style="float: left;font-style: italic">

		<span style="padding-left: 60px">当前用户为：${USER_IN_SESSION.username}</span>
		<span style="padding-left: 30px"><a href="/cancel" class="a">注销</a></span>
	</div>
</div>

<script>
    var banners = ["/images/discount.jpg","/images/discount2.jpg","/images/discount3.jpg","/images/discount4.jpg"]; // 图片地址
    var counter = 0;
    function run(){
        setInterval(cycle,3000);  //重复运行cycle函数，周期1000ms
    }
    function cycle(){
        counter++;
        if(counter == banners.length)
            counter = 0;
        document.getElementById("banner").src = banners[counter];
    }
</script>

<div style="height: 100px;background-color: #eaefe1;line-height: 30px">
    <c:if test="${USER_IN_SESSION.type==0}">
        <span class="logo" style="padding-left:580px">mysterious&nbsp;&nbsp;bookstore</span>
        <span style="font-style: italic;font-size: 20px;padding-right: 80px;float: right">
		<a href="/manage/books?cmd=edit" class="a" style="padding-top:20px;display:inline-block">
			<span style="color: #fd6804;font-size: 40px">添加</span>
			<span style="color: #fd6804;font-size: 60px">+
		</span>
		</a>
	</span>
    </c:if>
    <c:if test="${USER_IN_SESSION.type==1}">
        <span style="font-style: italic;font-size: 20px;padding-right:80px;float: right">
		<a href="/shoppingCart" class="a" style="padding-top:20px;display:inline-block">
			<span style="color: #fd6804;font-size: 60px">$
			</span>
			<img src="/images/mycart.png" style="height:30px;" alt="购物车">购物车
		</a>
	</span>
    </c:if>
</div>
<br>
<div style="float:right;">
	<form action="/lookup">
		<input type="text" name="bookName" placeholder="图书查询" class="text" style="background-color: #fafbf8;">

		<input type="submit" class="search" value=""></span>
	</form>
	<br><br>
</div>
<a style="color: black; float:right;margin:10px 30px 0px 0px;text-decoration:none" href=${USER_IN_SESSION.type == 0 ? "/manage/books":"/customer/books"}>
	<input type="button" style="background-color: #108e99;border-radius: 10px;height: 100%;font-size:20px" value="返回首页">
</a>
<a href="#c4">
	<img id="banner" src="/images/discount.jpg" style="width: 1200px;height: 500px;padding-left:150px">
</a>


<div style="height: 60px"></div>
<%--<a href="#c4"><img src="/images/discount.jpg" style="width: 1200px;height: 500px;padding-left: 150px;"></a>--%>

<div style="padding-left: 160px;padding-top: 50px" >
	<a name="c4"></a>
	<c:forEach items="${lookupBooks}" var="b" varStatus="vs">

		<div style='width: 25%;height:430px;float: left'>

			<div><a href="/showBooks?id=${b.id}"><img src="/images/${b.book_image}" alt="加载失败" style="height: 230px;width: 180px"></a></div>
			<div style="height: 35px"></div>
			<div style="height: 25px"><a href="/showBooks?id=${b.id}" class="a">${b.book_name}</a></div>
			<div style="height:25px">
				<span style="font-size: 18px;font-style: italic;color: #c60f04">${b.book_price}</span>
						<c:if test="${USER_IN_SESSION.type==1}">
							<pre style="display: inline">    </pre>
							<a href="/shoppingCart?cmd=save&id=${b.id}&user_id=${USER_IN_SESSION.id}" class="a">
								<img src="/images/cart.png" style="width: 60px;height: 30px">
							</a>
						</c:if>
						<c:if test="${USER_IN_SESSION.type==0}">
							<span><a href="/manage/books?cmd=delete&id=${b.id}" class="a" style="padding-left: 25px">下架</a></span>
							<span><a href="/manage/books?cmd=edit&id=${b.id}" class="a" style="padding-left: 25px">编辑</a></span>
						</c:if>
				</div>
		</div>

	</c:forEach>
</div>
</body>
</html>



