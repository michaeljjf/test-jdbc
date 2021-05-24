package com.jiangjf.dao;

import com.jiangjf.util.DbUtil;
import com.jiangjf.util.JdbcPropertiesUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyConnectionPool {
    private static final String DRIVER = JdbcPropertiesUtil.getValue("driver");

    private static LinkedList<Connection> pool = null;
    private static int initSize = Integer.valueOf(JdbcPropertiesUtil.getValue("initSize"));
    private static int maxSize = Integer.valueOf(JdbcPropertiesUtil.getValue("maxSize"));

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 初始化连接池
        pool = new LinkedList<>();
        for (int i = 0; i < initSize; i++) {
            Connection connection = DbUtil.getConnection();
            if (connection != null) {
                pool.addLast(connection);
                System.out.println("初始化连接" + connection.hashCode());
            }
        }
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        if (pool.size() > 0) {
            connection = pool.removeFirst();
            System.out.println("从连接池拿出" + connection.hashCode());
        } else {
            connection = DbUtil.getConnection();
            System.out.println("连接池为空，创建连接" + connection.hashCode());
        }
        return connection;
    }

    /**
     * 归还连接
     * @param connection
     */
    public static void returnConnection(Connection connection) {
        if (connection != null) {
            try {
                if (connection.isClosed()) {
                    System.out.println("连接已关闭，无须归还" + connection.hashCode());
                } else {
                    if (pool.size() < maxSize) {
                        connection.setAutoCommit(true);// 重置事务设置
                        pool.addLast(connection);
                        System.out.println("连接池未满，归还连接" + connection.hashCode());
                    } else {
                        System.out.println("连接池已满，关闭链接" + connection.hashCode());
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("传入的参数为null");
        }
    }
}
