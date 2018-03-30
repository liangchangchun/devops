package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MachinePhysicalProbability;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.TDoll;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-02-11
 */
public interface IMachinePhysicalProbabilityService extends IService<MachinePhysicalProbability> {

    List<Map<String, Object>> selectLists(Page<MachinePhysicalProbability> page, Integer dollId, String name, String machineCode);

    MachinePhysicalProbability selectByDollId(Integer dollId);
}
