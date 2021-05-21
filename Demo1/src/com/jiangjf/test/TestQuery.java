package com.jiangjf.test;

import com.jiangjf.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestQuery {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/db_online_course?serverTimeZone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static void main(String[] args) {

        List<Teacher> teachers = testQuery();
        System.out.println(teachers);

    }

    public static List<Teacher> testQuery() {
        Connection connection = null;
        Statement statement = null;
        List<Teacher> list = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            String sql = "select id,nickName,remark,imageName from t_teacher";
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nickName = resultSet.getString("nickName");
                String remark = resultSet.getString("remark");
                String imageName = resultSet.getString("imageName");
//                System.out.println("id=" + id + "，nickName=" + nickName + "，remark=" + remark + "，imageName=" + imageName);
                Teacher teacher = new Teacher(id, nickName, remark, imageName);
                list.add(teacher);
            }
//            System.out.println(list);
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

        return list;
    }
}
