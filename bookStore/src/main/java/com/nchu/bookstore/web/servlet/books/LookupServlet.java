package com.nchu.bookstore.web.servlet.books;

import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lookup")
public class LookupServlet extends HttpServlet {
    BookService bookService = new BookService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        //System.out.println(bookName);

        List<Books> listBook = bookService.getBooksByName(bookName);
        //跳转到查询书本界面界面
        req.getSession().setAttribute("lookupBooks", listBook);
        //System.out.println("123");
        resp.sendRedirect("/jsp/lookup_list.jsp");
    }
}
