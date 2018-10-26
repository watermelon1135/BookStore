package com.nchu.bookstore.test;

import com.nchu.bookstore.dao.IBooksDAO;
import com.nchu.bookstore.dao.impl.BooksDAOimpl;
import com.nchu.bookstore.domain.Books;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class BooksDAOimplTest {
    private IBooksDAO dao = new BooksDAOimpl();

    @Test
    public void TestSave(){
        Books b = new Books();
       // b.setId(rs.getInt("id"));
        b.setBook_name("红楼梦");
        b.setBook_price(65);
        b.setBook_author("张三");
        b.setBook_pubhouse("不知道什么出版社");
        b.setBook_pubdate(new Date(2010,05,03));
        b.setBook_detail("这是一部红楼梦");
        b.setBook_image("img.png");
        dao.save(b);
    }

    @Test
    public void TestUpdate(){
        Books b = new Books();
        b.setBook_name("红楼梦");
        b.setBook_price(65);
        b.setBook_author("张e三");
        b.setBook_pubhouse("不知道什么出版社");
        b.setBook_pubdate(new Date(2010,05,03));
        b.setBook_detail("这是一部红楼梦");
        b.setBook_image("img.png");
        dao.update(7,b);
    }

    @Test
    public void TestDelete(){
        dao.delete(7);
    }

    @Test
    public void TestGet(){
        Books b;
        b=dao.get(10);
        System.out.println(b);
    }

    @Test
    public void TestList(){
        List<Books> list;
        list=dao.list();
        for (Books b:list) {
            System.out.println(b);
        }
    }
}
