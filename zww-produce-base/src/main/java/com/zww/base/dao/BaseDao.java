package com.zww.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.*;

public interface BaseDao {

	Boolean isWorker( Integer memberId);
	
	Member selectMemberById( Integer Id);
	Account selectAccountById( Integer Id);
	
	Member selectMemberByMemberId(String memberID);
	  /**
     * 根据id查询收货地址
     */
	MemberAddr selectMemberAddrByPrimaryKey(Integer id);
	
    /**
     * 获取默认地址
     */
    MemberAddr selectDefaultAddr(Integer memberId);
    
    Integer selectPayNumberByMemberId(Integer memberId);
    
    Doll selectDollById(Integer Id);
    
    RiskManagement selectRiskManagementById(Integer memberId);
    
    Integer selectRank(Integer id);
    
    List<DollOrder> selectDollOrderListByIds(Integer[] orderIds);
    
    int updateDollOrderByIdSelective(DollOrder record);
    
    int updateInviteStatus(Integer id);
    
    void updateChannel(@Param("memberId") int memberId, @Param("channel") String channel, @Param("inviter") Integer inviter, @Param("rank") Integer rank);
    
    void updateBitStatesById(@Param("id") int id, @Param("bitState") long bitState);
    
    //更新 周卡
    void updateMemberSeeksCardState(Account account);
    //更新 月卡
    void updateMemberMonthCardState(Account account);
}
