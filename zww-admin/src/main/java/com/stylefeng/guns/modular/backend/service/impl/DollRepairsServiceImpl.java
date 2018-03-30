package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.DollRepairs;
import com.stylefeng.guns.common.persistence.dao.DollRepairsMapper;
import com.stylefeng.guns.modular.backend.service.IDollRepairsService;
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
 * @author stylefeng
 * @since 2018-01-24
 */
@Service
public class DollRepairsServiceImpl extends ServiceImpl<DollRepairsMapper, DollRepairs> implements IDollRepairsService {

    @Autowired
    private DollRepairsMapper dollRepairsMapper;

    @Override
    public List<Map<String, Object>> selectDollRepairs(Page<DollRepairs> page) {
        return dollRepairsMapper.selectDollRepairs(page);
    }
}
