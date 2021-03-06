package com.jiangjf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil {
    /**
     * serverTimeZone：设置时区
     * characterEncoding：设置编码
     * rewriteBatchedStatements：是否开启批处理
     * useServerPrepStmts：是否开启预编译
     * cachePrepStmts：是否启用预编译缓存
     */
    private static final String URL = JdbcPropertiesUtil.getValue("url");
    private static final String USER = JdbcPropertiesUtil.getValue("user");
    private static final String PASSWORD = JdbcPropertiesUtil.getValue("password");

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库连接对象
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 关闭PreparedStatement对象
     * @param preparedStatement
     */
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
