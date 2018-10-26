package com.nchu.bookstore.web.servlet.books;

import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showBooks")
public class ShowBooksServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Books books = bookService.get(Integer.valueOf(id));
        req.setAttribute("book",books);
        req.getRequestDispatcher("/jsp/show_books.jsp").forward(req,resp);
    }
}
