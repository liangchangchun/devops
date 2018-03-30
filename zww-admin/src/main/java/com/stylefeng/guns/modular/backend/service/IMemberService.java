package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.Account;
import com.stylefeng.guns.common.persistence.model.Member;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
public interface IMemberService extends IService<Member> {

    Member selectIdByMemberId(String memberId);

    //用户列表
    List<Map<String, Object>> selectMember(@Param("page") Page<Member> page,@Param("id") Integer id, @Param("userId") String userId, @Param("userName") String userName, @Param("beginTime") String beginTime, @Param("lastLoginFrom") String lastLoginFrom);
    //渠道列表
    List<Map<String, Object>> selectChannel(@Param("page") Page<Member> page,String channelNum, @Param("userId") String userId, @Param("userName") String userName, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("lastLoginFrom") String lastLoginFrom);
    //小妖精渠道
    List<Map<String, Object>> xiaoyaojingSelectChannel(@Param("page") Page<Member> page,String channelNum, @Param("userId") String userId, @Param("userName") String userName, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("lastLoginFrom") String lastLoginFrom);

    Integer updateById(Account account);

    //封号
    Integer closeMember(Integer memberId);

    //解封
    Integer openMember(Integer memberId);
}
