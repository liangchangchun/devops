package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollRepairs;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-24
 */
public interface DollRepairsMapper extends BaseMapper<DollRepairs> {

    List<Map<String, Object>> selectDollRepairs(Page<DollRepairs> page);
}