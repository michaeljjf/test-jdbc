package com.jiangjf.dao;

import com.jiangjf.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BaseDao {
    /**
     * 抽取非查询的公共部分
     *
     * @param sql 执行的sql
     * @param args 可变参数
     * @return
     */
    public int baseUpdate(String sql, Object... args) {
        int rows = 0;
        Connection connection = MyConnectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            rows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DbUtil.closePreparedStatement(preparedStatement);
            MyConnectionPool.returnConnection(connection);
        }
        return rows;
    }
}
