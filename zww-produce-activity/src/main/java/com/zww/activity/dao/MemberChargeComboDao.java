package com.zww.activity.dao;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.MemberChargeCombo;



public interface MemberChargeComboDao {
    //查询有效套餐用户
    MemberChargeCombo selectMemberEffect(@Param("memberId") Integer MemberId, @Param("chargeType") Integer chargeType);

    void insertChargeCombo(MemberChargeCombo chargeCombo);

    void giveCoins(MemberChargeCombo combo);

    MemberChargeCombo selectByUserIdAndChargeType(@Param("memberId") Integer memberId, @Param("chargeType") Integer chargeType);

    void uodate(MemberChargeCombo memberChargeCombo);
}
