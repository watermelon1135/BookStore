package com.nchu.bookstore.service;

import com.nchu.bookstore.dao.IUserDAO;
import com.nchu.bookstore.dao.impl.UserDAOimpl;
import com.nchu.bookstore.domain.User;

public class UserService {
    private IUserDAO dao = new UserDAOimpl();

    public User isExist(String username) {
        return dao.getUserByUsername(username);
    }

    public boolean checkPassword(String password) {
        return false;
    }

    public void insert(User newUser) {
        dao.save(newUser);
    }


}
