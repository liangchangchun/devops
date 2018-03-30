package com.stylefeng.guns.common.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.Member;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 根据条件查询用户列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectMember(@Param("page") Page<Member> page,@Param("id") Integer id,@Param("userId") String userId, @Param("userName") String userName,@Param("beginTime") String beginTime,@Param("lastLoginFrom") String lastLoginFrom);

    //渠道
    List<Map<String, Object>> selectChannel(@Param("page") Page<Member> page,@Param("channelNum") String channelNum, @Param("userId") String userId, @Param("userName") String userName,@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("lastLoginFrom") String lastLoginFrom);

    //小妖精渠道
    List<Map<String, Object>> xiaoyaojingSelectChannel(@Param("page") Page<Member> page,@Param("channelNum") String channelNum, @Param("userId") String userId, @Param("userName") String userName,@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("lastLoginFrom") String lastLoginFrom);

    //跟据memberid查id
    Member selectIdByMemberId(@Param("memberId")String memberId);

    //查询用户金币
    Integer getCoinsById(Integer memberId);

    //获取渠道号
    List<Member> getRegisterChannel();
}