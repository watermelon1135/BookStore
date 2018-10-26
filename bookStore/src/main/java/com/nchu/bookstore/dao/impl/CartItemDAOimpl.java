package com.nchu.bookstore.dao.impl;

import com.nchu.bookstore.dao.ICartItemDAO;
import com.nchu.bookstore.domain.CartItem;
import com.nchu.bookstore.handler.ResultSetHandler;
import com.nchu.bookstore.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOimpl implements ICartItemDAO {
    @Override
    public void save(CartItem cartItem) {
        String sql = "INSERT INTO t_shoppingcart(user_id,book_id,book_name,book_price,book_author," +
                "book_pubhouse,book_pubdate,book_detail,book_image,number)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";
        JdbcTemplate.update(sql,cartItem.getUser_id(),cartItem.getBook_id(), cartItem.getBook_name(),
                cartItem.getBook_price(),cartItem.getBook_author(), cartItem.getBook_pubhouse(),
                cartItem.getBook_pubdate(),cartItem.getBook_detail(),cartItem.getBook_image(),
                cartItem.getNumber());

    }

    @Override
    public void delete(int id) {
        String sql = "delete from t_shoppingcart where id = ?";
        JdbcTemplate.update(sql,id);
    }

    @Override
    public void update(int id, CartItem cartItem) {
        String sql = "update t_shoppingcart Set user_id=?,book_id=?,book_name=?,book_price=?,book_author=?," +
                "book_pubhouse=?,book_pubdate=?,book_detail=?,book_image=?,number=? where id = ?";
        JdbcTemplate.update(sql,cartItem.getUser_id(),cartItem.getBook_id(), cartItem.getBook_name(),
                cartItem.getBook_price(),cartItem.getBook_author(), cartItem.getBook_pubhouse(),
                cartItem.getBook_pubdate(),cartItem.getBook_detail(),cartItem.getBook_image(),
                cartItem.getNumber(),id);
    }

    @Override
    public CartItem get(int id) {
        String sql = "select * from t_shoppingcart where id = ?";

        return JdbcTemplate.query(sql, new ResultSetHandler<CartItem>() {
            public CartItem Handler(ResultSet rs) throws SQLException {
                if (rs.next()){
                    CartItem c = new CartItem();
                    c.setId(id);
                    c.setUser_id(rs.getInt("user_id"));
                    c.setBook_id(rs.getInt("book_id"));
                    c.setBook_name(rs.getString("book_name"));
                    c.setBook_price(rs.getDouble("book_price"));
                    c.setBook_author(rs.getString("book_author"));
                    c.setBook_pubhouse(rs.getString("book_pubhouse"));
                    c.setBook_pubdate(rs.getDate("book_pubdate"));
                    c.setBook_detail(rs.getString("book_detail"));
                    c.setBook_image(rs.getString("book_image"));
                    c.setNumber(rs.getInt("number"));
                    return c;
                }
                return null;
            }
        },id);
    }

    @Override
    public List<CartItem> list(int user_id) {
        String sql = "select * from t_shoppingcart where user_id = ?;";
        List<CartItem> list = new ArrayList<>();
        return JdbcTemplate.query(sql, new ResultSetHandler<List<CartItem>>() {
            public List<CartItem> Handler(ResultSet rs) throws SQLException {
                while (rs.next()){
                    CartItem c = new CartItem();
                    list.add(c);
                    c.setId(rs.getInt("id"));
                    c.setUser_id(rs.getInt("user_id"));
                    c.setBook_id(rs.getInt("book_id"));
                    c.setBook_name(rs.getString("book_name"));
                    c.setBook_price(rs.getDouble("book_price"));
                    c.setBook_author(rs.getString("book_author"));
                    c.setBook_pubhouse(rs.getString("book_pubhouse"));
                    c.setBook_pubdate(rs.getDate("book_pubdate"));
                    c.setBook_detail(rs.getString("book_detail"));
                    c.setBook_image(rs.getString("book_image"));
                    c.setNumber(rs.getInt("number"));
                }
                return list;
            }
        },user_id);
    }
}
