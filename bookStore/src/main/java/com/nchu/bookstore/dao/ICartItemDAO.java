package com.nchu.bookstore.dao;

import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.domain.CartItem;

import java.util.List;

public interface ICartItemDAO {
    /**
     *
     * @param cartItem 要保存的商品信息信息
     */
    public void save(CartItem cartItem);

    /**
     *
     * @param id 要删除的商品id
     */
    public void delete(int id);

    /**
     *
     * @param id 要修改的商品id
     * @param cartItem 修改之后的商品信息
     */
    public void update(int id, CartItem cartItem);

    /**
     *
     * @param id 要查询商品的id
     * @return 查询到的商品信息
     */
    public CartItem get(int id);

    /**
     *
     * @return 指定用户所有商品信息的集合
     */
    public List<CartItem> list(int user_id);
}
