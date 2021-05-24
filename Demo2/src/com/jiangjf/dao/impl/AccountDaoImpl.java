package com.jiangjf.dao.impl;

import com.jiangjf.dao.AccountDao;
import com.jiangjf.dao.BaseDao;
import com.jiangjf.dao.MyConnectionPool;
import com.jiangjf.pojo.Account;
import com.jiangjf.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Override
    public int add(Account account) {
        String sql = "insert into t_account(id,name,money) values(default, ?, ?)";
        return baseUpdate(sql, account.getName(), account.getMoney());
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from t_account where id = ?";
        return baseUpdate(sql, id);
    }

    @Override
    public Account getOneById(int id) {
        Account account = null;
        Connection connection = MyConnectionPool.getConnection();
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
            MyConnectionPool.returnConnection(connection);
        }
        return account;
    }

    @Override
    public List<Account> getList() {
        List<Account> accountList = null;
        Connection connection = MyConnectionPool.getConnection();
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
            MyConnectionPool.returnConnection(connection);
        }
        return accountList;
    }
}
