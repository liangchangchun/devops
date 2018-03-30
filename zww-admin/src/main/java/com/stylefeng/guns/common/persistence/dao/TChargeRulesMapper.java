package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TChargeRules;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-17
 */
public interface TChargeRulesMapper extends BaseMapper<TChargeRules> {

    List<Map<String, Object>> selectRulesList(@Param("page") Page<TChargeRules> page);

}