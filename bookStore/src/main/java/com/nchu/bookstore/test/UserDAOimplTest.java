package com.nchu.bookstore.test;

import com.nchu.bookstore.dao.IUserDAO;
import com.nchu.bookstore.dao.impl.UserDAOimpl;
import com.nchu.bookstore.domain.User;
import org.junit.jupiter.api.Test;

public class UserDAOimplTest {
    private IUserDAO dao = new UserDAOimpl();
    @Test
    public void TestgetUserByUsername(){
        System.out.println(dao.getUserByUsername("admin"));
    }

    @Test
    public void TestSave(){
        User u = new User();
        u.setUsername("xixi");
        u.setPassword("123");
        dao.save(u);
    }

}
