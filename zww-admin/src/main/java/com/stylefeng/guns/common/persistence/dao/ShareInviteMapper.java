package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.ShareInvite;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-26
 */
public interface ShareInviteMapper extends BaseMapper<ShareInvite> {

	List<Map<String, Object>> selectMember(@Param("page") Page<ShareInvite> page,@Param("createDate")  String createDate, @Param("endDate")String endDate,@Param("memberid") String memberid,@Param("name") String name);

	Integer selectInvitedNum(@Param("userId") String userId);
	
	Date selectInvitedTime(@Param("userId") String userId);

	List<Map<String, Object>> selectInvitedMember(@Param("page") Page<ShareInvite> page,@Param("createDate") String createDate,@Param("endDate")String endDate,@Param("memberid") String memberid,
			@Param("name") String name);
}