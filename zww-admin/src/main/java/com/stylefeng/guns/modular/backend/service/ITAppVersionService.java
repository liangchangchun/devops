package com.stylefeng.guns.modular.backend.service;

import com.stylefeng.guns.common.persistence.model.TAppVersion;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-15
 */
public interface ITAppVersionService extends IService<TAppVersion> {

    TAppVersion selectByAppKey(String sys);
    boolean updates(TAppVersion tAppVersion);
    boolean updateByAppKey(TAppVersion tAppVersion);
}
