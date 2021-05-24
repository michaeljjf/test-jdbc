package com.jiangjf;

import com.jiangjf.dao.AccountDao;
import com.jiangjf.dao.MyConnectionPool;
import com.jiangjf.dao.impl.AccountDaoImpl;
import com.jiangjf.pojo.Account;

import java.sql.Connection;
import java.util.List;

public class Test {
    public static void main(String[] args) {

//        Connection connection = MyConnectionPool.getConnection();
//        Connection connection2 = MyConnectionPool.getConnection();
//        Connection connection3 = MyConnectionPool.getConnection();
//        Connection connection4 = MyConnectionPool.getConnection();
//        Connection connection5 = MyConnectionPool.getConnection();
//
//        MyConnectionPool.returnConnection(connection);
//        MyConnectionPool.returnConnection(connection2);
//        MyConnectionPool.returnConnection(connection3);
//        MyConnectionPool.returnConnection(connection4);
//        MyConnectionPool.returnConnection(connection5);

        AccountDao accountDao = new AccountDaoImpl();

        // 添加
        Account account = new Account();
        account.setName("蒋建飞");
        account.setMoney(10000.0);
        accountDao.add(account);

        account = accountDao.getOneById(1);
        System.out.println(account);

        accountDao.deleteById(1);

        // 获取列表
        List<Account> accountList = accountDao.getList();
        System.out.println(accountList);
    }
}
