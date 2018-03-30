package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TChargeRules;
import com.stylefeng.guns.common.persistence.dao.TChargeRulesMapper;
import com.stylefeng.guns.modular.backend.service.ITChargeRulesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-17
 */
@Service
public class TChargeRulesServiceImpl extends ServiceImpl<TChargeRulesMapper, TChargeRules> implements ITChargeRulesService {

    @Autowired
    private TChargeRulesMapper tChargeRulesMapper;

    @Override
    public List<Map<String, Object>> selectRulesList(Page<TChargeRules> page) {
        return tChargeRulesMapper.selectRulesList(page);
    }

    @Override
    public boolean insert(TChargeRules tChargeRules){
        tChargeRules.setCreatedDate(new Date());
        return retBool(tChargeRulesMapper.insert(tChargeRules));
    }

    @Override
    public boolean updateById(TChargeRules tChargeRules){
        tChargeRules.setModifiedDate(new Date());
        return retBool(tChargeRulesMapper.updateById(tChargeRules));
    }
}
