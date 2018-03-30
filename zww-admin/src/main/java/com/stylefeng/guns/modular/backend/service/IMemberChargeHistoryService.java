package com.stylefeng.guns.modular.backend.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.Account;
import com.stylefeng.guns.common.persistence.model.MemberChargeHistory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-03
 */
public interface IMemberChargeHistoryService extends IService<MemberChargeHistory> {
    List<Map<String, Object>> selectList(Page<MemberChargeHistory> page,Integer condition);

    //生成消费记录
    Integer insertChargeHistory(Account account);
}
