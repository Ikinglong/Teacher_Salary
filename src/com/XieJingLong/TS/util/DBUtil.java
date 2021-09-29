package com.XieJingLong.TS.util;

/**
JDBC 工具类，方便日后操作
 */

import java.sql.*;

public class DBUtil {
//工具类的构造函数一般都是私有的，因为工具类的方法都是静态的，不需new对象
    private DBUtil(){

    }

//Register the SQL Driver.and in the Static coding block,only execute once when the class was loaded.
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the SQL Connection Object
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        String url="jdbc:mysql://127.0.0.1:3306/teacher_salary";
        String user="root";
        String password="123456";
        Connection conn= DriverManager.getConnection(url,user,password);
        return conn;
    }

    /**
     * Close the opening Resource
     * @param conn
     * @param ps
     * @param rs
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Start Transaction
     * @param conn
     * @throws SQLException
     */
   public static void beginTransaction(Connection conn) throws SQLException{
        if(conn!=null){
            conn.setAutoCommit(false);
        }
   }

    /**
     * Commit Transaction
     * @param conn
     * @throws SQLException
     */
   public static void commitTransaction(Connection conn) throws SQLException{
        if(conn!=null){
            conn.commit();
        }
   }

    /**
     * Roll back Transaction
     * @param conn
     */
   public static void rollbackTransaction(Connection conn){
       if(conn!=null){
           try {
               conn.rollback();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
}
