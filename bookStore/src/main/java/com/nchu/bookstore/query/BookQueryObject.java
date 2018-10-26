package com.nchu.bookstore.query;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

//书本查询对象
@Data
public class BookQueryObject extends QueryObject{
     private String keyword;//关键字查询 作者或者书名

    @Override
    public void customziedQuery() {
        if (StringUtils.isNotBlank(keyword)){
            super.addQuery(" (book_name LIKE ? OR book_author LIKE ?)","%"+keyword+"%","%"+keyword+"%");
        }
    }
}
