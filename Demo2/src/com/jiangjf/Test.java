package com.jiangjf;

import com.jiangjf.dao.AccountDao;
import com.jiangjf.dao.impl.AccountDaoImpl;
import com.jiangjf.pojo.Account;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        AccountDao accountDao = new AccountDaoImpl();
        Account account = new Account();
        account.setName("蒋建飞");
        account.setMoney(10000.0);
        accountDao.add(account);

        List<Account> accountList = accountDao.getList();
        System.out.println(accountList);
    }
}
