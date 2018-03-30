package com.stylefeng.guns.modular.backend.service;

import com.stylefeng.guns.common.persistence.model.TSystemPref;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-17
 */
public interface ITSystemPrefService extends IService<TSystemPref> {
    TSystemPref selectByCode(String code);
    boolean updateByCode(TSystemPref tSystemPref);
    boolean deleteByCode(TSystemPref tSystemPref);
}
