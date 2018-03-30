package com.zww.account.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zww.account.dao.AccountDao;
import com.zww.account.dao.MemberDao;
import com.zww.account.service.AccountService;
import com.zww.api.model.*;

/**
 * Created by SUN on 2018/1/10.
 */
@Service("AccountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private MemberDao memberDao;

    /**
     * 根据id查询账户信息
     *
     * @param id
     * @return
     */
    @Override
    public Account selectById(Integer id) {
        Account account = accountDao.selectById(id);
        if (account == null) {
            createAccount(id);
            account = accountDao.selectById(id);
        }
        return account;
    }
    /*
     *  public ResultMap selectById(Integer id) {
        Account account = accountDao.selectById(id);
        if (account == null) {
            createAccount(id);
            account = accountDao.selectById(id);
        }
        return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, account);
    }
     */

    @Override
    public Account select(Integer id) {
        return accountDao.selectById(id);
    }

    /**
     * 创建用户
     *
     * @param id
     */
    @Override
    public Account createAccount(Integer id) {
        Account account = new Account();
        account.setId(id);
        Integer coinsById = memberDao.getCoinsById(id);
        account.setCoins(coinsById == null ? 0 : coinsById);
        accountDao.insert(account);
        return account;
    }

    @Override
    public void insert(Account account) {
        accountDao.insert(account);
    }

    @Override
    public void updateMemberCoin(Account account) {
        accountDao.updateMemberCoin(account);
    }
    
    @Override
    public void updateMemberSuperTicket(Account account) {
        accountDao.updateMemberSuperTicket(account);
    }
    
    @Override
    public void updateMemberSeeksCardState(Account account) {
        accountDao.updateMemberSeeksCardState(account);
    }
    
    @Override
    public void updateMemberMonthCardState(Account account) {
        accountDao.updateMemberMonthCardState(account);
    }
    

    @Override
    public Integer selectId(int id) {
        return accountDao.selectId(id);
    }

    @Override
    public void updateBitStatesById(Account account) {
        accountDao.updateBitStatesById(account);
    }

}
