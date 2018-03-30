package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MachinePhysicalProbability;
import com.stylefeng.guns.common.persistence.dao.MachinePhysicalProbabilityMapper;
import com.stylefeng.guns.modular.backend.service.IMachinePhysicalProbabilityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-02-11
 */
@Service
public class MachinePhysicalProbabilityServiceImpl extends ServiceImpl<MachinePhysicalProbabilityMapper, MachinePhysicalProbability> implements IMachinePhysicalProbabilityService {

    @Autowired
    private MachinePhysicalProbabilityMapper machinePhysicalProbabilityMapper;

    @Override
    public List<Map<String, Object>> selectLists(Page<MachinePhysicalProbability> page, Integer dollId,String name, String machineCode) {
        return machinePhysicalProbabilityMapper.selectLists(page, dollId, name, machineCode);
    }

    @Override
    public MachinePhysicalProbability selectByDollId(Integer dollId) {
        return machinePhysicalProbabilityMapper.selectByDollId(dollId);
    }
}
