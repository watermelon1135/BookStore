package com.nchu.bookstore.web.servlet.books;

import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.domain.CartItem;
import com.nchu.bookstore.service.BookService;
import com.nchu.bookstore.service.ShopingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {
    private BookService bookService;
    private ShopingCartService shopingCartService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
        shopingCartService = new ShopingCartService();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String cmd = req.getParameter("cmd");
        if("save".equals(cmd)){
            save(req,resp);
        }else if("delete".equals(cmd)){
            delete(req,resp);
        }else if("shopping".equals(cmd)){
            req.getRequestDispatcher("/jsp/customer_list.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/jsp/cart_list.jsp").forward(req, resp);
        }
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int book_id = Integer.valueOf(req.getParameter("id"));//书本id
        int user_id = Integer.valueOf(req.getParameter("user_id"));//用户id
        Books book = bookService.get(Integer.valueOf(book_id));
        //判断该商品在不在购物车里面
        List<CartItem> list = shopingCartService.list(user_id);
        //System.out.println(list);
        for (CartItem it:list) {
            if (it.getUser_id()==user_id && it.getBook_id()==book_id){//商品已经在购物车中存在
                it.setNumber(it.getNumber()+1);//修改购买数量
                shopingCartService.update(it.getId(),it);//修改数据库
                //req.getSession().setAttribute("SESSION_IN_CART",shopingCartService.list(user_id));
                resp.sendRedirect("/shoppingCart");
                return ;
            }
    }
    shopingCartService.save(new CartItem(
            user_id,book.getId(),book.getBook_name(),book.getBook_price(),book.getBook_author()
            ,book.getBook_pubhouse(),book.getBook_pubdate(),book.getBook_detail(),book.getBook_image(),1
    ));
        resp.sendRedirect("/shoppingCart");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        shopingCartService.delete(Integer.valueOf(id));
        resp.sendRedirect("/shoppingCart");
    }
}
