package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.DollInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-04-02
 */
public interface DollInfoMapper extends BaseMapper<DollInfo> {
    DollInfo selectByDollCode(@Param("dollCode") String dollCode);
}