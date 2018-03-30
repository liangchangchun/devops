package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.TSystemPref;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-17
 */
public interface TSystemPrefMapper extends BaseMapper<TSystemPref> {

    TSystemPref selectByPrimaryKey(@Param("code") String code);
    Integer updateByCode(TSystemPref tSystemPref);
    Integer deleteByCode(TSystemPref tSystemPref);
}