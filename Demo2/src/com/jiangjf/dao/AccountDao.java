package com.jiangjf.dao;

import com.jiangjf.pojo.Account;

import java.util.List;

public interface AccountDao {
    /**
     * 添加账号
     * @param account 要添加数据封闭成Account类的对象
     * @return 添加成功，返回大于0的整数，失败返回0
     */
    int add(Account account);
    int deleteById(int id);
    Account getOneById(int id);
    List<Account> getList();
}
