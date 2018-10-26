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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取用户名 两次密码 用户类型
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String type = req.getParameter("type");
        String inputCode = req.getParameter("inputCode") ;
        String code = (String) req.getSession().getAttribute("code") ;
        //System.out.println(password);
        //System.out.println(confirmPassword);
        //首先验证密码
        if (!inputCode.equalsIgnoreCase(code)){
            req.setAttribute("errorMsg","验证码输入有误");
            req.getRequestDispatcher("/Register.jsp").forward(req,resp);
            return ;
        }
        User newUser = new User();
        newUser.setUsername(username);
        User u = userService.isExist(username);
        if (u != null){
            //用户名被注册了
            req.setAttribute("errorMsg","该账号已经存在了哦");
            req.getRequestDispatcher("/Register.jsp").forward(req,resp);
            return ;
        }
        else{
            //用户名没有被注册
            //检查用户名长度是否合法
            if (username.length()<4 || username.length()>16){
                req.setAttribute("errorMsg","亲，用户名有效字符为4到16个");
                req.getRequestDispatcher("/Register.jsp").forward(req,resp);
                return ;
            }
            else{
                //检查密码长度是否合法
                if (password.length()<6 || password.length()>16){
                    req.setAttribute("errorMsg","亲，密码为6位到16位");
                    req.getRequestDispatcher("/Register.jsp").forward(req,resp);
                    return ;
                }
                else{
                    //检查密码是否一致
                    if (!password.equals(confirmPassword)){
                        req.setAttribute("errorMsg","两次密码不一样");
                        req.getRequestDispatcher("/Register.jsp").forward(req,resp);
                        return ;
                    }
                }
            }
        }
        //设置用户基本信息
        newUser.setPassword(StringHelper.encrypt(password));
        newUser.setType(Integer.valueOf(type));
        userService.insert(newUser);
        req.setAttribute("errorMsg", "注册成功！请登录");
        req.getRequestDispatcher("/Login.jsp").forward(req,resp);
    }
}
