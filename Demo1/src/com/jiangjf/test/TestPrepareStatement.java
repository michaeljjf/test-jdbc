package com.jiangjf.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试预编译方式CRUD
 */
public class TestPrepareStatement {
    public static void main(String[] args) {
        testAdd();
    }

    public static void testAdd() {
        Connection connection = DbUtil.getConnection();
        String sql = "insert into t_teacher(nickName, remark, imageName) values(?, ? ,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "蒋建飞");
            preparedStatement.setString(2, "牛B的人");
            preparedStatement.setString(3, "test.jpg");
            int rows = preparedStatement.executeUpdate();
            System.out.println("受影响的行数：" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
