package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.TSystemPref;
import com.stylefeng.guns.common.persistence.dao.TSystemPrefMapper;
import com.stylefeng.guns.modular.backend.service.ITSystemPrefService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-17
 */
@Service
public class TSystemPrefServiceImpl extends ServiceImpl<TSystemPrefMapper, TSystemPref> implements ITSystemPrefService {

    @Autowired
    private TSystemPrefMapper systemPrefMapper;

    @Override
    public TSystemPref selectByCode(String code) {
        return systemPrefMapper.selectByPrimaryKey(code);
    }

    @Override
    public boolean updateByCode(TSystemPref tSystemPref) {
        return retBool(systemPrefMapper.updateByCode(tSystemPref));
    }

    @Override
    public boolean deleteByCode(TSystemPref tSystemPref) {
        return retBool(systemPrefMapper.deleteByCode(tSystemPref));
    }
}
