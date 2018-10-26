package com.nchu.bookstore.dao;


import com.nchu.bookstore.domain.User;

public interface IUserDAO {
		User getUserByUsername(String username);
		void save(User user);
}
