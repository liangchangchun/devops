package com.zww.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.Charge;
import com.zww.api.model.ShareInvite;


/**
 * @author lgq
 *         Version: 1.0
 *         Date: 2017/9/22
 *         Description: 用户Dao接口类.
 *         Copyright (c) 2017 伴飞网络. All rights reserved.
 */
public interface ChargeDao {

    Charge getChargeRules(Double chargePrice);

    Integer insertChargeHistory(Charge charge);

    List<Charge> getChargeHistory(Integer memberId);

    Integer updateMemberCount(Charge charge);

    Integer insertInviteChargeHistory(Charge charge);

    Integer updateInviteMemberCount(Charge charge);

    Integer totalCount(@Param("memberId") Integer memberId);

    Integer getChargeByInviteCode(@Param("inviteCode") String inviteCode);

    List<Charge> getChargeList(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize, @Param("memberId") Integer memberId);

    Integer insertInvite(ShareInvite invite);

    void inviteChargeFirst(Map<String, Integer> parameterMap);

    Integer getChargeByMchOrderNo(String mchOrderNo);

    String whoInvite(String memberId);

    Integer howInvite(String memberId);

    /**
     * 统一回调
     */
    void payNotify(Map<String, Object> parameterMap);

}
