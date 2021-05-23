package com.jiangjf.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 抽取数据库工具类
 */
public class DbUtil {
    private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
    /**
     * serverTimeZone：设置时区
     * characterEncoding：设置编码
     * rewriteBatchedStatements：是否开启批处理
     * useServerPrepStmts：是否开启预编译
     * cachePrepStmts：是否启用预编译缓存
     */
    private static final String URL = "jdbc:mysql://localhost:3306/db_online_course?serverTimeZone=UTC&characterEncoding=utf-8&rewriteBatchedStatements=true&useServerPrepStmts=true&cachePrepStmts=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVE);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
