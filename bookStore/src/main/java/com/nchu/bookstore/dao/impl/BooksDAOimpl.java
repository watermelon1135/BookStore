package com.nchu.bookstore.dao.impl;

import com.nchu.bookstore.dao.IBooksDAO;
import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.handler.ResultSetHandler;
import com.nchu.bookstore.query.PageResult;
import com.nchu.bookstore.query.QueryObject;
import com.nchu.bookstore.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDAOimpl implements IBooksDAO {
    @Override
    public void save(Books books) {
        String sql = "INSERT INTO t_book(book_name,book_price,book_author,book_pubhouse,book_pubdate,book_detail,book_image)" +
                "VALUES(?,?,?,?,?,?,?);";
        JdbcTemplate.update(sql,
                books.getBook_name(),books.getBook_price(),books.getBook_author(),
                books.getBook_pubhouse(),books.getBook_pubdate(),books.getBook_detail(),books.getBook_image());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from t_book where book_id = ?";
        JdbcTemplate.update(sql,id);
    }

    @Override
    public void update(int id, Books books) {
        String sql = "update t_book Set book_name=?,book_price=?,book_author=?,book_pubhouse=?," +
                "book_pubdate=?,book_detail=?,book_image=?" +
                " where book_id = ?";
        JdbcTemplate.update(sql,books.getBook_name(),books.getBook_price(),books.getBook_author(),
                books.getBook_pubhouse(),books.getBook_pubdate(),books.getBook_detail(),books.getBook_image(),id);
    }

    @Override
    public Books get(int id) {
        String sql = "select * from t_book where book_id = ?";

        return JdbcTemplate.query(sql, new ResultSetHandler<Books>() {
            public Books Handler(ResultSet rs) throws SQLException {
                if (rs.next()){
                    Books b = new Books();
                    b.setId(id);
                    b.setBook_name(rs.getString("book_name"));
                    b.setBook_price(rs.getDouble("book_price"));
                    b.setBook_author(rs.getString("book_author"));
                    b.setBook_pubhouse(rs.getString("book_pubhouse"));
                    b.setBook_pubdate(rs.getDate("book_pubdate"));
                    b.setBook_detail(rs.getString("book_detail"));
                    b.setBook_image(rs.getString("book_image"));
                    return b;
                }
                return null;
            }
        },id);
    }

    @Override
    public List<Books> list() {
        String sql = "select * from t_book;";
        List<Books> list = new ArrayList<>();
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
                    b.setBook_detail(rs.getString("book_detail"));
                    b.setBook_image(rs.getString("book_image"));
                }
                return list;
            }
        });
    }

    //根据查询对象获取结果集
    @Override
    public PageResult quert(QueryObject qo) {
        //获取结果总数
        String querySql=qo.getQuery();
        String countSql = "SELECT COUNT(*) FROM t_book"+querySql;
        System.out.println("countSql:"+countSql);
        System.out.println("参数："+qo.getParameters());
        Integer totalCount = JdbcTemplate.query(countSql,new ResultSetHandler<Long>(){
            public Long Handler(ResultSet rs) throws SQLException {
                if (rs.next()){
                    return rs.getLong(1);
                }
                return 0L;
            }
        },qo.getParameters().toArray()).intValue();
        if (totalCount == 0){
            return PageResult.empty(qo.getPageSize());
        }
        qo.getParameters().add((qo.getCurrentPage()-1)*qo.getPageSize());
        qo.getParameters().add(qo.getPageSize());
        String resultSql = "SELECT * FROM t_book "+querySql+" LIMIT ?,?";
        System.out.println("resultSql:"+resultSql);
        System.out.println("参数："+qo.getParameters());
        List<Books> list = new ArrayList<>();
        List<Books> lisyData= JdbcTemplate.query(resultSql, new ResultSetHandler<List<Books>>() {
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
                    b.setBook_detail(rs.getString("book_detail"));
                    b.setBook_image(rs.getString("book_image"));
                }
                return list;
            }
        },qo.getParameters().toArray());
        //获取所有结果集

        return new PageResult(lisyData,totalCount,qo.getCurrentPage(),qo.getPageSize());
    }
}
