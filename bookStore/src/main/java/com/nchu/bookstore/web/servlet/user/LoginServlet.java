package com.nchu.bookstore.web.servlet.user;

import com.nchu.bookstore.domain.User;
import com.nchu.bookstore.service.UserService;
import com.nchu.bookstore.util.StringHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //表示用户两个类型
        final String MANAGER = "manager";
        final int MANAGERINT = 0;
        final String CUSTOMER = "customer";
        final int CUSTOMERINT= 1;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType");
        //System.out.println(username);
        //检查该账号是否存在
        User user = userService.isExist(username);
        //System.out.println(user);
        if (user == null){
            req.setAttribute("errorMsg", "该"+username+"账号不存在");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            return;
        }
        //检查密码是否正确
        if(!user.getPassword().equals(StringHelper.encrypt(password))){
            req.setAttribute("errorMsg", "账号或密码不正确");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            return;
        }
        //检查登录类型
        //如果选择的是管理员 检查登录账户是不是管理员类型
        if (MANAGER.equals(userType)){
            if (user.getType() == MANAGERINT){
                //登录账户放入session
                req.getSession().setAttribute("USER_IN_SESSION",user);
                resp.sendRedirect("/manage/books");
            }else{
                req.setAttribute("errorMsg", "未选择正确的用户类型");
                req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            }
        }
        else{
            if (user.getType() == CUSTOMERINT){
                //登录账户放入session
                req.getSession().setAttribute("USER_IN_SESSION",user);
                resp.sendRedirect("/customer/books");
            }else{
                req.setAttribute("errorMsg", "未选择正确的用户类型");
                req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            }
        }
    }
}
