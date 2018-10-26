package com.nchu.bookstore.domain;

import lombok.Data;

import java.util.Date;

//购物车的商品对象和普通商品对象不一样
@Data
public class CartItem {
    private int id;//商品ID
    private int user_id;//用户ID
    private int book_id;//书本ID
    private String book_name;
    private double book_price;
    private String book_author;
    private String book_pubhouse;
    private Date book_pubdate;
    private String book_detail;
    private String book_image;
    private Integer number;//购买数量

    public CartItem() {
    }

    public CartItem(int user_id, int book_id, String book_name, double book_price, String book_author, String book_pubhouse, Date book_pubdate, String book_detail, String book_image, Integer number) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_price = book_price;
        this.book_author = book_author;
        this.book_pubhouse = book_pubhouse;
        this.book_pubdate = book_pubdate;
        this.book_detail = book_detail;
        this.book_image = book_image;
        this.number = number;
    }
}
