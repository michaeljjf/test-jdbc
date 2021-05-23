package com.jiangjf.dao;

import com.jiangjf.pojo.Account;

import java.util.List;

public interface AccountDao {
    int add(Account account);
    int deleteById(int id);
    Account getOneById(int id);
    List<Account> getList();
}
