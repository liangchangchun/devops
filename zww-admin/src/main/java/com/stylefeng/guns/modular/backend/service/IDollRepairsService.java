package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollRepairs;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.TChargeRules;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-24
 */
public interface IDollRepairsService extends IService<DollRepairs> {

    List<Map<String, Object>> selectDollRepairs(Page<DollRepairs> page);

}
