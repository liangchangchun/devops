package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.stylefeng.guns.common.persistence.model.TChargeRules;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-17
 */
public interface ITChargeRulesService extends IService<TChargeRules> {

    List<Map<String, Object>> selectRulesList(Page<TChargeRules> page);

    boolean insert(TChargeRules tChargeRules);

    boolean updateById(TChargeRules tChargeRules);
}
