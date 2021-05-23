package com.jiangjf.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestBatch {
    public static void main(String[] args) {
        testBatch();
    }

    /**
     * 测试批处理
     */
    public static void testBatch() {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into t_teacher(id,nickName,remark,imageName) values(default, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= 10100; i++) {
                preparedStatement.setString(1, "nickName");
                preparedStatement.setString(2, "remark");
                preparedStatement.setString(3, "t.jpg");
                preparedStatement.addBatch();
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }
}
