package com.nchu.bookstore.service;

import com.nchu.bookstore.dao.ICartItemDAO;
import com.nchu.bookstore.dao.impl.CartItemDAOimpl;
import com.nchu.bookstore.domain.CartItem;
import com.nchu.bookstore.util.JdbcTemplate;

import java.util.List;

public class ShopingCartService {
    private ICartItemDAO dao = new CartItemDAOimpl();
    public void delete(Integer id) {
        dao.delete(id);
    }

    public void deleteAll(Integer id) {
        String sql = "delete from t_shoppingcart where user_id = ?";
        JdbcTemplate.update(sql,id);
    }

    public void update(Integer integer, CartItem book) {
        dao.update(integer,book);
    }

    public void save(CartItem book) {
        dao.save(book);
    }

    public List<CartItem> list(int user_id) {
        return dao.list(user_id);
    }

    public CartItem get(Integer integer) {
        return dao.get(integer);
    }
}
