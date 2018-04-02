package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.MemberVip;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-28
 */
public interface MemberVipMapper extends BaseMapper<MemberVip> {

    Integer deleteByMemberVipId(@Param("id") Integer id);
    MemberVip selectListByMemberId(@Param("userId") Integer userId);

}