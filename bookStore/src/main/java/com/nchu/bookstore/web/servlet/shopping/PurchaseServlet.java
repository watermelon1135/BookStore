package com.nchu.bookstore.web.servlet.shopping;

import com.nchu.bookstore.domain.User;
import com.nchu.bookstore.service.ShopingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/purchase")
//处理结算界面
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ShopingCartService shopingCartService = new ShopingCartService();
       // req.getServletContext().removeAttribute("SESSION_IN_CART");
       // req.getServletContext().removeAttribute((String) req.getServletContext().getAttribute("userCart"));

        User user = (User)req.getSession().getAttribute("USER_IN_SESSION");
        int user_id = user.getId();
        shopingCartService.deleteAll(user_id);
        resp.getWriter().print("<h1>结算成功，3s后回到购物车</h1>");
        resp.setHeader("refresh", "3;url=/jsp/cart_list.jsp");
    }
}
