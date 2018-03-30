package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.persistence.dao.AccountMapper;
import com.stylefeng.guns.common.persistence.dao.DollAddressMapper;
import com.stylefeng.guns.common.persistence.dao.MemberMapper;
import com.stylefeng.guns.common.persistence.model.Account;
import com.stylefeng.guns.common.persistence.model.DollAddress;
import com.stylefeng.guns.modular.backend.service.IAccountService;
import com.stylefeng.guns.modular.backend.service.IDollAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account createAccount(Integer id) {
        Account account = new Account();
        account.setId(id);
        Integer coinsById = memberMapper.getCoinsById(id);
        account.setCoins(coinsById == null ? 0 : coinsById);
        accountMapper.insert(account);
        return account;
    }
}
