<%@ page import="com.nchu.bookstore.service.ShopingCartService" %>
<%@ page import="com.nchu.bookstore.domain.User" %>
<%@ page import="com.nchu.bookstore.domain.ShoppingCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>购物车列表</title>
    <script src="../js/showTime.js"></script>
</head>
<body>
    <%
    ShopingCartService shopingCartService = new ShopingCartService();
    User user = (User)request.getSession().getAttribute("USER_IN_SESSION");
    int user_id = user.getId();
    request.getSession().setAttribute("SESSION_IN_CART",new ShoppingCart(shopingCartService.list(user_id)));
%>
<div style="background-color: rgba(12,141,64,0.53);height: 5%;">
    <span id="showTime" style="float:left;margin-left:100px;margin-top:6px"></span>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a style="" href="/shoppingCart"><input type="button" value="书本种类：${SESSION_IN_CART.size}" style="float: right;background-color: #8effdc;border-radius: 5px;height: 100%;font-size:20px"></a>
    <img style="float: right;height: 100%;" src="images/gwc.png">

    <span style="font-size: 20px;font-family: 微软雅黑;margin:300px">
            <c:if test="${not empty SESSION_IN_CART.listItem && SESSION_IN_CART.size!=0}">
                您的购物车有<span style="color: #c60f04;">${SESSION_IN_CART.size}</span>件商品<br>
            </c:if>
<c:if test="${empty SESSION_IN_CART.listItem || SESSION_IN_CART.size==0 }">
    您的购物车中还没有商品，快去
    <a href="/shoppingCart?cmd=shopping">购物<img src="../images/cart_logo.png" style="width: 15px;height: 15px;"></a>
</c:if>
    </span>
</div>
<div style="background-image: url('/images/cartbg.jpg');width:100%;height:210%;background-repeat: repeat;position: absolute">
    <div style="padding-right: 15%;padding-left: 15%;height: 100%;background:rgba(255,255,255,0.3);backgroud-attachemnt:fixed">

        <div>
            <table style="width: 100%;" frame="below">
                <tr>
                    <th>商品信息</th>
                    <th>单价（元）</th>
                    <th>数量</th>
                    <th>金额</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${SESSION_IN_CART.listItem}" var="item">
                    <tr align="center" bgcolor="#adacff">
                        <td><a href="/showBooks?id=${item.book_id} " style="text-decoration: none">
                            <img src="/images/${item.book_image}" style="height:100px;width:70px;padding-right: 20px"
                                 align="center"
                            >
                        </a>
                            <a href="/showBooks?id=${item.book_id}"
                               style="text-decoration: none;height:100px;width:70px;padding-right: 20px">${item.book_name}</a></td>
                        <td>${item.book_price}</td>
                        <td>${item.number}</td>
                        <td>${item.book_price*item.number}</td>
                        <td><a style="text-decoration: none" href="/shoppingCart?cmd=delete&id=${item.id}">移出购物车</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6" align="right">购物车总价：${SESSION_IN_CART.totalPrice}</td>
                </tr>
            </table>
            <a style="text-decoration:none;margin:300px" href="/customer/books;">
                <input type="button" value="我要购物" style="background-color: #b0acb0;border-radius: 10px;height: 40px;width: 100px;;font-size:20px"/>
            </a>
            <a href="/purchase">
                <input type="submit" value="去结算"
                       style="float: right;height: 40px;width: 100px;border-radius: 5px;background-color:#f10707">
            </a>
        </div>
    </div>
</div>