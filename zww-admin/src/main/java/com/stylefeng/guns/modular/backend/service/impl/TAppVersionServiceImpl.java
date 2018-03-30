package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.TAppVersion;
import com.stylefeng.guns.common.persistence.dao.TAppVersionMapper;
import com.stylefeng.guns.modular.backend.service.ITAppVersionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-15
 */
@Service
public class TAppVersionServiceImpl extends ServiceImpl<TAppVersionMapper, TAppVersion> implements ITAppVersionService {

    @Autowired
    private TAppVersionMapper tAppVersionMapper;

    @Override
    public TAppVersion selectByAppKey(String sys) {
        return tAppVersionMapper.selectByAppKey(sys);
    }

    @Override
    public boolean updates(TAppVersion tAppVersion) {
        return retBool(tAppVersionMapper.updates(tAppVersion));
    }

    @Override
    public boolean updateByAppKey(TAppVersion tAppVersion) {
        return retBool(tAppVersionMapper.updateByAppKey(tAppVersion));
    }
}
