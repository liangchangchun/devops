package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.TAppVersion;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-15
 */
public interface TAppVersionMapper extends BaseMapper<TAppVersion> {

    TAppVersion selectByAppKey(@Param("sys") String sys);
    Integer updates(TAppVersion tAppVersion);
    Integer updateByAppKey(TAppVersion tAppVersion);
}