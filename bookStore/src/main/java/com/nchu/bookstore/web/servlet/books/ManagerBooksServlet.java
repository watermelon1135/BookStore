package com.nchu.bookstore.web.servlet.books;

import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.query.BookQueryObject;
import com.nchu.bookstore.query.PageResult;
import com.nchu.bookstore.service.BookService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/manage/books")
public class ManagerBooksServlet extends HttpServlet {

	private BookService bookService;

	public void init() throws ServletException {
		bookService=new BookService();
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//获取cmd参数
		String cmd = req.getParameter("cmd");
		//判断session中是否有用户信息
        Object uin = req.getSession().getAttribute("USER_IN_SESSION");
        if (uin == null){
            req.setAttribute("errorMsg","你还没有登陆，请登录！");
            req.getRequestDispatcher("/Login.jsp").forward(req,resp);
            return ;
        }
        //请求分发操作
        if ("save".equals(cmd)){
			try {
				saveOrUpdate(req, resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if ("delete".equals(cmd)){
			delete(req, resp);
		}else if("edit".equals(cmd)){
			edit(req, resp);
		}else{
			list(req, resp);
		}
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		bookService.delete(Integer.valueOf(req.getParameter("id")));
		resp.sendRedirect("/manage/books");
	}
	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ParseException {
		Books book = new Books();
		//System.out.println(req);
		String sid=req.getParameter("id");
		book.setBook_name(req.getParameter("name"));
		book.setBook_author(req.getParameter("title"));
		Date dyr = new Date(new SimpleDateFormat(
				"yyyy-mm-dd").parse(req.getParameter("yr")).getTime());
		System.out.println(dyr.toLocaleString());
		book.setBook_pubdate(dyr);
		book.setBook_price(Double.valueOf(req.getParameter("price")));
		book.setBook_pubhouse(req.getParameter("book_pubhouse"));
		book.setBook_detail(req.getParameter("book_detail"));
		book.setBook_image(req.getParameter("book_image"));
		//判断是保存还是更新操作
		if (!"".equals(sid)&&sid!=null){
			bookService.update(Integer.valueOf(sid), book);
		}else{
			bookService.save(book);
		}
		resp.sendRedirect("/manage/books");
	}

	protected void list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//跳转到商品界面
		BookQueryObject qo = new BookQueryObject();
		this.queryToObject(req,qo);
		req.getSession().setAttribute("qo",qo);
		PageResult pageResult = bookService.query(qo);
		req.getSession().setAttribute("pageResult", pageResult);
		// System.out.println("123");
		resp.sendRedirect("/jsp/list.jsp");
		//req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sid = req.getParameter("id");
		if (!"".equals(sid) && sid!=null){
			Books b;
			b = bookService.get(Integer.valueOf(sid));
			req.setAttribute("book", b);
		}
		//resp.sendRedirect("/jsp/edit.jsp");
		req.getRequestDispatcher("/jsp/edit.jsp").forward(req, resp);
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
