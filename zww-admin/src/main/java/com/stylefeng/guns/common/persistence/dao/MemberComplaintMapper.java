package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberComplaint;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
public interface MemberComplaintMapper extends BaseMapper<MemberComplaint> {

    //待处理
    List<Map<String, Object>> selectMemberComplain(@Param("page") Page<MemberComplaint> page, @Param("memberNum") String memberNum, @Param("complaintChannel") String complaintChannel,@Param("catchStates") String catchStates,@Param("vipGroup") Double vipGroup);

    //已处理
    List<Map<String, Object>> selectMemberComplainDone(@Param("page") Page<MemberComplaint> page, @Param("memberNum") String memberNum, @Param("complaintChannel") String complaintChannel,@Param("catchStates") String catchStates,@Param("checkState") Integer checkState);

}