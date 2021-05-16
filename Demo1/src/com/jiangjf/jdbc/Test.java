package com.jiangjf.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/db_online_course?serverTimeZone=UTC";
    private static String user = "root";
    private static String password = "123456";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String sql = "insert into t_teacher(id,nickName,remark,imageName) values(null,'test','test','test');";
            int rows = statement.executeUpdate(sql);
            System.out.println("受影响的行数：" + rows);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
