package com.nchu.bookstore.dao.impl;

import com.nchu.bookstore.dao.IUserDAO;
import com.nchu.bookstore.domain.User;
import com.nchu.bookstore.handler.ResultSetHandler;
import com.nchu.bookstore.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOimpl implements IUserDAO {
    /**
     *
     * @param username 根据用户名在数据库查找是否存在
     * @return 该用户名对应的用户对象 不存在返回null
     */
    public User getUserByUsername(String username) {
        String sql = "select * from t_user where username=?";
        return JdbcTemplate.query(sql, new ResultSetHandler<User>() {
            public User Handler(ResultSet rs) throws SQLException {
                if (rs.next()){
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setPassword(rs.getString("password"));
                    u.setType(rs.getInt("type"));
                    return u;
                }
                return null;
            }
        },username);
    }

    @Override
    public void save(User user) {
        String sql = "insert into t_user(username,password,type) values(?,?,?)";
        JdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getType());
    }
}
