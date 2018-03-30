package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MachineProbability;
import com.stylefeng.guns.common.persistence.dao.MachineProbabilityMapper;
import com.stylefeng.guns.modular.backend.service.IMachineProbabilityService;
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
 * @author konghuanhuan
 * @since 2018-01-29
 */
@Service
public class MachineProbabilityServiceImpl extends ServiceImpl<MachineProbabilityMapper, MachineProbability> implements IMachineProbabilityService {

    @Autowired
    private MachineProbabilityMapper machineProbabilityMapper;

    @Override
    public List<Map<String, Object>> selectMachineProbability(Page<MachineProbability> page, Integer dollId, String machainCode, String name) {
        return machineProbabilityMapper.selectMachineProbability(page,dollId,machainCode,name);
    }

    @Override
    public MachineProbability selectByDollId(Integer dollId) {
        return machineProbabilityMapper.selectByDollId(dollId);
    }
}
