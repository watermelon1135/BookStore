package com.nchu.bookstore.test;

import com.nchu.bookstore.dao.ICartItemDAO;
import com.nchu.bookstore.dao.impl.CartItemDAOimpl;
import com.nchu.bookstore.domain.CartItem;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class CartItemDAOimplTest {
    private ICartItemDAO dao = new CartItemDAOimpl();

    @Test
    public void TestSave(){
        CartItem b = new CartItem();
        // b.setId(rs.getInt("id"));
        b.setUser_id(5);
        b.setBook_id(3);
        b.setBook_name("红楼梦");
        b.setBook_price(65);
        b.setBook_author("张三");
        b.setBook_pubhouse("不知道什么出版社");
        b.setBook_pubdate(new Date(2010,05,03));
        b.setBook_detail("这是一部红楼梦");
        b.setBook_image("img.png");
        b.setNumber(1);
        dao.save(b);
    }

    @Test
    public void TestUpdate(){
        CartItem b = new CartItem();
        // b.setId(rs.getInt("id"));
        b.setUser_id(5);
        b.setBook_id(3);
        b.setBook_name("红楼梦");
        b.setBook_price(65);
        b.setBook_author("张三");
        b.setBook_pubhouse("不知道什么出版社");
        b.setBook_pubdate(new Date(2010,05,03));
        b.setBook_detail("这是一部红楼梦");
        b.setBook_image("img.png");
        b.setNumber(2);
        dao.update(1,b);
    }

    @Test
    public void TestDelete(){
        dao.delete(1);
    }

    @Test
    public void TestGet(){
        CartItem b;
        b=dao.get(3);
        System.out.println(b);
    }

    @Test
    public void TestList(){
        List<CartItem> list;
        list=dao.list(5);
        for (CartItem b:list) {
            System.out.println(b);
        }
    }
}
