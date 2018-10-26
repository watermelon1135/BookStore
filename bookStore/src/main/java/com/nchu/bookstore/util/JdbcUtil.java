package com.nchu.bookstore.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
    private JdbcUtil(){

    }

    private static Properties p = new Properties();
    private static DataSource dataSource = null;
    //静态代码块只做一次
    static {
        try {
            p.load(Thread.currentThread().
                    getContextClassLoader().getResourceAsStream("db.properties"));
            dataSource=DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection  getConn(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection conn,Statement st,ResultSet rs){
        try{
            if (rs!=null) rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if (st!=null) st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }finally{
                try{
                    if (conn!=null) conn.close();
                }catch (Exception e) {
                }
            }
        }
    }
}
