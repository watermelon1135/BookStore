package com.nchu.bookstore.web.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancel")
public class CancelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //注销操作
        //方式1.(用得少)
        //req.getSession().removeAttribute("USER_IN_SESSION");
        //方式2.(比较多)
        //使session过期
        req.getSession().invalidate();
        resp.sendRedirect("/Login.jsp");
    }
}
