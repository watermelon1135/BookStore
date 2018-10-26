package com.nchu.bookstore.domain;

import lombok.Data;

import java.util.Date;

/**
 * 图书信息类
 */
@Data
public class Books {
    private int id;
    private String book_name;
    private double book_price;
    private String book_author;
    private String book_pubhouse;
    private Date book_pubdate;
    private String book_detail;
    private String book_image;

    public Books(){

    }

}
