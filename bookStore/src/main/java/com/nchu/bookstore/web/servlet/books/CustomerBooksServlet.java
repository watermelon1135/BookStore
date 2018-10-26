package com.nchu.bookstore.web.servlet.books;

import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.query.BookQueryObject;
import com.nchu.bookstore.query.PageResult;
import com.nchu.bookstore.query.QueryObject;
import com.nchu.bookstore.service.BookService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/books")
public class CustomerBooksServlet extends HttpServlet {
    private BookService bookService;

    public void init() throws ServletException {
        bookService=new BookService();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //跳转到商品界面
        BookQueryObject qo = new BookQueryObject();
        this.queryToObject(req,qo);
        req.getSession().setAttribute("qo",qo);
        PageResult pageResult = bookService.query(qo);
        req.getSession().setAttribute("pageResult", pageResult);
       // System.out.println("123");
        resp.sendRedirect("/jsp/customer_list.jsp");
        //req.getRequestDispatcher("/WEB-INF/views/customer_list.jsp").forward(req, resp);
    }

    private void queryToObject(HttpServletRequest req, BookQueryObject qo) {
        String keyword = req.getParameter("keyword");
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        if (StringUtils.isNotBlank(keyword)){
            qo.setKeyword(keyword);
        }
        if (StringUtils.isNotBlank(currentPage)){
            qo.setCurrentPage(Integer.valueOf(currentPage));
        }

        if (StringUtils.isNotBlank(pageSize)){
            qo.setPageSize(Integer.valueOf(pageSize));
        }
    }

}
