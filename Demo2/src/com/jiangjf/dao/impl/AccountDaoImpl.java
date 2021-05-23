package com.jiangjf.dao.impl;

import com.jiangjf.dao.AccountDao;
import com.jiangjf.pojo.Account;
import com.jiangjf.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    @Override
    public int add(Account account) {
        int rows = 0;
        if (account == null) {
            return rows;
        }
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into t_account(id,name,money) values(default, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setDouble(2, account.getMoney());
            rows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DbUtil.closePreparedStatement(preparedStatement);
            DbUtil.closeConnection(connection);
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        int rows = 0;
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from t_account where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DbUtil.closePreparedStatement(preparedStatement);
            DbUtil.closeConnection(connection);
        }
        return rows;
    }

    @Override
    public Account getOneById(int id) {
        Account account = null;
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select id,name,money from t_account where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Double money = resultSet.getDouble("money");
                account = new Account(id, name, money);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DbUtil.closePreparedStatement(preparedStatement);
            DbUtil.closeConnection(connection);
        }
        return account;
    }

    @Override
    public List<Account> getList() {
        List<Account> accountList = null;
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select id,name,money from t_account";
        try {
            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            accountList = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double money = resultSet.getDouble("money");
                Account account = new Account(id, name, money);
                accountList.add(account);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DbUtil.closePreparedStatement(preparedStatement);
            DbUtil.closeConnection(connection);
        }
        return accountList;
    }
}
