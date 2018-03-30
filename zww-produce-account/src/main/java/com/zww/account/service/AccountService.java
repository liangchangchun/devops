package com.zww.account.service;

import com.zww.api.model.Account;

/**
 * Created by SUN on 2018/3/2.
 * 用户账户相关服务类接口
 */
public interface AccountService {

	Account selectById(Integer id);

    Account select(Integer id);

    Account createAccount(Integer id);

    void insert(Account account);
    /**
     * 修改hi币
     * @param account
     */
    void updateMemberCoin(Account account);
    /**
     * 修改钻石
     * @param account
     */
    void updateMemberSuperTicket(Account account);
    /**
     * 更新周卡
     * @param account
     */
    void updateMemberSeeksCardState(Account account);
    /**
     * 更新月卡
     * @param account
     */
    void updateMemberMonthCardState(Account account);

    Integer selectId(int id);

    void updateBitStatesById(Account account);
}
