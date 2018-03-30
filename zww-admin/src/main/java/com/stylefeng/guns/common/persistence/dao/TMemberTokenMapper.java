package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.TMemberToken;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-02-24
 */
public interface TMemberTokenMapper extends BaseMapper<TMemberToken> {

    //token失效
    Integer deleteByWhere(@Param("memberId") Integer memberId);
    //查询token
    List<String> selectTokenById(@Param("memberId") Integer memberId);
}