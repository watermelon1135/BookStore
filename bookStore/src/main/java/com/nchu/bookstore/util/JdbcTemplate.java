package com.nchu.bookstore.util;


import com.nchu.bookstore.handler.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JdbcTemplate {
    private JdbcTemplate(){

    }

    public static int update(String sql,Object ...param){
        Connection conn=null;
        PreparedStatement ps=null;

        try{
            conn = JdbcUtil.getConn();
            ps=conn.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                ps.setObject(i+1, param[i]);
            }
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        } finally{
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    public static <T> T query(String sql, ResultSetHandler<T> rsh, Object ...param){

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            conn = JdbcUtil.getConn();
            ps=conn.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                ps.setObject(i+1, param[i]);
            }
            rs=ps.executeQuery();
            //模板不能处理结果集 交给结果处理集对象
            return rsh.Handler(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally{
          JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}
