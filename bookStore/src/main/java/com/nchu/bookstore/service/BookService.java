package com.nchu.bookstore.service;

import com.nchu.bookstore.dao.IBooksDAO;
import com.nchu.bookstore.dao.impl.BooksDAOimpl;
import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.handler.ResultSetHandler;
import com.nchu.bookstore.query.PageResult;
import com.nchu.bookstore.query.QueryObject;
import com.nchu.bookstore.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private IBooksDAO dao = new BooksDAOimpl();

    public void delete(Integer id) {
        dao.delete(id);
    }

    public void update(Integer integer, Books book) {
        dao.update(integer,book);
    }

    public void save(Books book) {
        dao.save(book);
    }

    public List<Books> list() {
        return dao.list();
    }

    public Books get(Integer integer) {
        return dao.get(integer);
    }

    public List<Books> getBooksByName(String bookName) {
        List<Books> list = new ArrayList<>();
        String sql = "select * from t_book where book_name like '%"+bookName+"%'";
        //System.out.println(sql);
        return JdbcTemplate.query(sql, new ResultSetHandler<List<Books>>() {
            public List<Books> Handler(ResultSet rs) throws SQLException {
                while (rs.next()){
                    Books b = new Books();
                    list.add(b);
                    b.setId(rs.getInt("book_id"));
                    b.setBook_name(rs.getString("book_name"));
                    b.setBook_price(rs.getDouble("book_price"));
                    b.setBook_author(rs.getString("book_author"));
                    b.setBook_pubhouse(rs.getString("book_pubhouse"));
                    b.setBook_pubdate(rs.getDate("book_pubdate"));
                    b.setBook_image(rs.getString("book_image"));
                    b.setBook_detail(rs.getString("book_detail"));
                }
                return list;
            }
        });
    }

    public PageResult query(QueryObject qo){
        return dao.quert(qo);
    }
}
