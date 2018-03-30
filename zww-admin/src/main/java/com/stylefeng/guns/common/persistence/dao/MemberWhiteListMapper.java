package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberWhiteList;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.TDoll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-03
 */
public interface MemberWhiteListMapper extends BaseMapper<MemberWhiteList> {

    MemberWhiteList selectIdByMemberId(@Param("memberId") String memberId);

    List<Map<String, Object>> selectLists(@Param("page") Page<MemberWhiteList> page, @Param("memberId") String memberId, @Param("userName") String userName);
}