package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.TMemberAddr;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-25
 */
public interface TMemberAddrMapper extends BaseMapper<TMemberAddr> {

    /**
     * 获取默认地址
     */
    TMemberAddr selectDefaultAddr(@Param("memberId") Integer memberId);
}