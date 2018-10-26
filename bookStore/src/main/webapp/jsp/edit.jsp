
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>管理界面</title>
	<style>

		.block {
			width: 400px;
			display: block;
			margin: 5px 0;
		}

		.block_center {
			text-align: right ;
		}

		label {
			display: inline-block;
			font-size: 20px;
			height: 50px;
			width: 100px;
			font-weight: bolder;
			text-align: center;
			color: black;
		}

		input {
			vertical-align: middle;
			width: 200px;
			height: 40px;
		}

		button {
			background: #108ee9;
			color: white;
			width: 80px;
			height: 40px;
			border-radius: 5px;
			font-weight: bolder;
		}
	</style>
	<script src="../js/showTime.js"></script>
</head>
<body>
<div class="right" style="width: 33.33333%;float:left">
</div>
<div style="background-color: #108ee9;height: 35px">

	<div style="height: 40px;width:25%;float:left; "></div>
	<div style="width:50%; color:#ffffff;float:left;
    overflow: hidden; height: 40px;line-height: 40px;">
		<div style="float:left;font-family: 微软雅黑;font-size: 18px;font-weight: bold"><span id="showTime" style="margin-right:100px;margin-top:6px;font-size:16px"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;亲爱的管理员[${USER_IN_SESSION.username}],欢迎你!
		</div>
		<div style="float:right">
			<a href="/cancel" style="text-decoration: none;color: white;font-weight: bold;font-size: 18px">注销</a>
		</div>
	</div>

	<div style="height: 40px;width:25%;float:left "></div>

</div>

<div style="width:100%;height: 100%;margin: 0px auto ;padding: auto;background-image:url('/images/book3.jpg');background-size: 100%">

	<div class="left" style="width: 33.33333%;float: left;margin: 0px">
		<img src="/images/logo.png" style="width: 240px;height: 160px">
	</div>

	<div class="mid" style="width:33.33333%;float: left">
		<br/>
		<div style="display: block;margin: 5px 0 ">
			<table style="border:0px #fef4ff solid ">
				<td>
					<form action="/upload" method="post" enctype="multipart/form-data">
						<div style="font-size: 15px;font-family: 微软雅黑">
							<input type="hidden" name='id' value="${book.id}">
							<h2 style="color:#108ee9;font-family: 微软雅黑;font-weight: bolder;margin-left:25%">
								请填写图书的相关信息</h2>
							<div class="block">
								<label> 图书名称：</label>
								<input type="text" name="name" value="${book.book_name}" placeholder="请在这里输入图书的名称"><br/>
							</div>
							<hr/>
							<div class="block">
								<label> 作者：</label>
								<input type="text" name="title" value="${book.book_author}"
									   placeholder="请在这里输入图书的作者"><br/>
							</div>
							<hr/>
							<div class="block">
								<label>价格：</label>
								<input type="text" name="price" value="${book.book_price}"
									   placeholder="请在这里输入图书的价格"><br/>
							</div>
							<hr/>
							<div class="block">
								<label>出版日期：</label>
								<input type="date" name="yr" value="${book.book_pubdate}"><br/>
							</div>
							<hr/>
							<div class="block">
								<label> 出版社：</label>
								<input type="text" name="book_pubhouse" value="${book.book_pubhouse}"
									   placeholder="请在这里输入图书的出版社信息"><br/>
							</div>
							<hr/>
							<div class="block">
								<label>书本简介：</label>
								<input type="text" name="book_detail" value="${book.book_detail}" style="height: 100px"
									   placeholder="请在这里输入书本简介"><br/>
							</div>
							<hr/>
							<div class="block">
								<c:if test="${empty book.book_detail}">
									<label>书本图片：</label>
									<input type="file" name="book_image">
								</c:if>
								<c:if test="${not empty book.book_detail}">
									<input type="hidden" name="book_image" value="${book.book_image}">
								</c:if>
							</div>
							<hr/>
							<div class="block_center">
								<a href="/manage/books" style="color:black;text-decoration: none;margin-left: 70px;float:left;font-size:25px;color:white;width:100px;height:40px;text-align:center">
										返回
								</a>
								<button type="submit" style="margin-left: 80px">提交</button>
							</div>
						</div>
					</form>


				</td>
			</table>
		</div>
		</form>
	</div>

	<div class="right" style="width: 33.33333%;float:left">
	</div>

</div>

</body>
</html>