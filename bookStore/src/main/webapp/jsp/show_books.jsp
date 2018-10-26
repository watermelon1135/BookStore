<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息</title>
</head>
<body>
<div style="background-color: rgba(168,161,161,0.34);height: 5%;">
    <a style="color: black" href=${USER_IN_SESSION.type == 0 ? "/manage/books":"/customer/books"}>
        <input type="button" style="background-color: #108e99;border-radius: 10px;height: 100%;font-size:20px"
                                                          value="返回首页"></a>
    <a style="color: black;float: right" href="/shoppingCart"><input type="button"
                                                                     style="background-color: #f12d1b;border-radius: 5px;height: 100%;font-size: 20px"
                                                                     value="购物车"></a>
    <img style="float: right;height: 100%;" src="images/gwc.png">
</div>

<div style="background-image: url('/images/bookimage.jpg');background-repeat: no-repeat;background-size: 100%;position: relative">
    <div style="height:100%;padding-left:15%;padding-right:15%;padding-top:10%;background:rgba(255,255,255,0.3);">

        <div style="">
            <img src="/images/${book.book_image}" alt="加载失败" style="width:300px; height:400px;float: left">
        </div>

        <div style="font-weight: bolder;float: left;height: 10%;width: 70%;font-size:40px">
            ${book.book_name}
        </div>

        <div style="height: 25%;width: 70%;float: left;font-size: large;">
            ${book.book_detail}
        </div>

        <div style="height: 5%;width: 70%;float: left;font-size: small">
            作者：${book.book_author}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            出版社:${book.book_pubhouse}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            出版日期:${book.book_pubdate}
        </div>

        <div style="height:10%;width:70%;font-size: 40px;color: #f12d1b;float:left;background-color: rgba(191,255,240,0.32)">
            ¥${book.book_price}
        </div>

        <div style="height: 5%;width: 70%;float:right;padding: 0px">
            <img src="images/cart.png" height="100%" style="float:left;">
            <c:if test="${USER_IN_SESSION.type == 1}">
                <a href="/shoppingCart?cmd=save&id=${book.id}&user_id=${USER_IN_SESSION.id}">
                    <input style="float:left;height:100%;background-color: #4f8d05;border-radius: 5px" type="submit"
                           value="添加到购物车">
                </a>
            </c:if>
            <c:if test="${USER_IN_SESSION.type == 0}">
                <a href="/manage/books">
                    <input style="float:left;height:100%;background-color: #4f8d05;border-radius: 5px" type="submit"
                           value="返回">
                </a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
