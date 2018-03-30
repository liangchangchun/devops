package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.stylefeng.guns.common.persistence.model.MachinePhysicalProbability;
import com.stylefeng.guns.common.persistence.model.MachineProbability;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-29
 */
public interface IMachineProbabilityService extends IService<MachineProbability> {

    List<Map<String, Object>> selectMachineProbability(Page<MachineProbability> page,Integer dollId, String machainCode, String name);
    MachineProbability selectByDollId(Integer dollId);
}
