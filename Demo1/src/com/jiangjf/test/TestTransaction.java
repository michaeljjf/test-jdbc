package com.jiangjf.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) {
        testTransaction();
    }

    /**
     * 测试事务
     */
    public static void testTransaction() {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update t_account set money = money - ? where id = ?";
        try {
            connection.setAutoCommit(false);// jdbc默认自动提交事务,这里关闭后,手动提交
            preparedStatement = connection.prepareStatement(sql);

            // 转出
            preparedStatement.setDouble(1, 100);
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();

            // 加入异常（两边账户都不会变化，因为有事务处理）
//            int i = 1 / 0;

            // 转入
            preparedStatement.setDouble(1, -100);
            preparedStatement.setInt(2, 2);
            preparedStatement.executeUpdate();
            connection.commit();// 没有异常,提交事务
        } catch (Exception exception) {
            if (connection != null) {
                try {
                    connection.rollback();// 有异常,回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            exception.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
