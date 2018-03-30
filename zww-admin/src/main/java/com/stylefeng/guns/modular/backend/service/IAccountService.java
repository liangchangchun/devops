package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.Account;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-24
 */
public interface IAccountService extends IService<Account> {
    Account createAccount(Integer id);
}
