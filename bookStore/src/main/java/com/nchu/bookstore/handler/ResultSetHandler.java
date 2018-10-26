package com.nchu.bookstore.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

//定义处理结果集对象
public interface ResultSetHandler<T> {
	T Handler(ResultSet rs) throws  SQLException;
}
