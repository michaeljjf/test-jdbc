package com.jiangjf.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试预编译方式CRUD
 */
public class TestPreparedStatement {
    public static void main(String[] args) {
//        testAdd();
//        testUpdate();
//        testDelete();
        testQuery(11);
    }

    public static void testAdd() {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into t_teacher(id, nickName, remark, imageName) values(999, ?, ? ,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "蒋建飞");
            preparedStatement.setString(2, "牛B的人");
            preparedStatement.setString(3, "test.jpg");
            int rows = preparedStatement.executeUpdate();
            System.out.println("受影响的行数：" + rows);
        } catch (SQLException exception) {
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

    public static void testUpdate() {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update t_teacher set nickName = ?, remark = ?, imageName = ? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "张三");
            preparedStatement.setString(2, "测试");
            preparedStatement.setString(3, "t.jpg");
            preparedStatement.setInt(4, 999);
            int rows = preparedStatement.executeUpdate();
            System.out.println("受影响的行数：" + rows);
        } catch (SQLException exception) {
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

    public static void testDelete() {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from t_teacher where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 999);
            int rows = preparedStatement.executeUpdate();
            System.out.println("受影响的行数：" + rows);
        } catch (SQLException exception) {
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

    public static void testQuery(int id) {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select id,nickName,remark,imageName from t_teacher where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nickName = resultSet.getString("nickName");
                String remark = resultSet.getString("remark");
                String imageName = resultSet.getString("imageName");
                System.out.println("nickName=" + nickName + ", remark=" + remark + ", imageName=" + imageName);
            } else {
                System.out.println("未找到id=" + id + "的记录");
            }
        } catch (SQLException exception) {
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
