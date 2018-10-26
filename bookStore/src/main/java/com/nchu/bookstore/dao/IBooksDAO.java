package com.nchu.bookstore.dao;

import com.nchu.bookstore.domain.Books;
import com.nchu.bookstore.query.PageResult;
import com.nchu.bookstore.query.QueryObject;

import java.util.List;

public interface IBooksDAO {
    /**
     *
     * @param books 要保存的图书信息
     */
    public void save(Books books);

    /**
     *
     * @param id 要删除的图书id
     */
    public void delete(int id);

    /**
     *
     * @param id 要修改的图书id
     * @param books 修改之后的图书信息
     */
    public void update(int id, Books books);

    /**
     *
     * @param id 要查询图书的id
     * @return 查询到的图书信息
     */
    public Books get(int id);

    /**
     *
     * @return 所有图书信息的集合
     */
    public List<Books> list();


    public PageResult quert(QueryObject queryObject);
}
