package com.jiangjf.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.LinkedList;

public class TestSavePoint {
    public static void main(String[] args) {
        testBatch();
    }

    /**
     * 测试批处理(回滚到某个点)
     */
    public static void testBatch() {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into t_teacher(id,nickName,remark,imageName) values(default, ?, ?, ?)";
        LinkedList<Savepoint> savepointLinkedList = new LinkedList<>();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= 10100; i++) {
                preparedStatement.setString(1, "nickName");
                preparedStatement.setString(2, "remark");
                preparedStatement.setString(3, "t.jpg");
                preparedStatement.addBatch();
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    Savepoint savepoint = connection.setSavepoint();
                    savepointLinkedList.addLast(savepoint);
                }
                // 人为抛出异常来测试
                if (i == 10001) {
                    int row = 1 / 0;
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
        } catch (Exception exception) {
            if (connection != null) {
                // 假设回滚到第5个回滚点
                Savepoint sp = savepointLinkedList.get(4);
                if (sp != null) {
                    try {
                        connection.rollback(sp);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        connection.rollback();
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }

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
                    connection.commit();
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }
}
