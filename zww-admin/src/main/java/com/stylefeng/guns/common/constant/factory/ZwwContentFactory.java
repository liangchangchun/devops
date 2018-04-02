package com.stylefeng.guns.common.constant.factory;

import com.stylefeng.guns.common.persistence.dao.*;
import com.stylefeng.guns.common.persistence.model.*;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.core.util.SpringContextHolder;

@Component
@DependsOn("springContextHolder")
public class ZwwContentFactory {

	private MemberVipMapper memberVipMapper = SpringContextHolder.getBean(MemberVipMapper.class);

	private MemberMapper memberMapper = SpringContextHolder.getBean(MemberMapper.class);
	private TChargeRulesMapper tChargeRulesMapper = SpringContextHolder.getBean(TChargeRulesMapper.class);
	private AccountMapper accountMapper = SpringContextHolder.getBean(AccountMapper.class);
	
	private ShareInviteMapper shareInviteMapper = SpringContextHolder.getBean(ShareInviteMapper.class);;

	private TDollMapper tDollMapper = SpringContextHolder.getBean(TDollMapper.class);;

	public static ZwwContentFactory me() {
	        return SpringContextHolder.getBean("zwwContentFactory");
	    }
	  
	   /**
	    * 用户id 获取 memberId
	    * @param userId
	    * @return
	    */
	 public String getMemberId(Integer userId) {
		 Member member =  memberMapper.selectById(userId);
		 if (member != null) {
	            return member.getMemberID();
	        } else {
	            return "--";
	        }
	 }

	/**
	 * 用户id 获取 金币，钻石
	 * @param userId
	 * @return
	 */
	public Account getAccountById(Integer userId) {
		Account account =  accountMapper.selectById(userId);
		if (account != null) {
			return account;
		} else {
			return null;
		}
	}
	/**
	 * 获得充值规则
	 * @param payIndex
	 * @return
	 */
	public TChargeRules getTChargeRules(Integer payIndex) {
		// TODO Auto-generated method stub
		return tChargeRulesMapper.selectById(payIndex);
	}
	/**
	 * 获得邀请人数
	 * @param userId
	 * @return
	 */
	public Integer getInvitedNum(String userId) {
		return shareInviteMapper.selectInvitedNum(userId);
	}
	
	public Date getInvitedTime(String userId) {
		return shareInviteMapper.selectInvitedTime(userId);
	}


//	/**
//	 * 获取机器号，机器名
//	 */
//
//	public TDoll getTDoll(Integer dollId){
//		return tDollMapper.selectById(dollId);
//	}

	/**
	 * 获取某一用户vip等级
	 *
	 */
	public MemberVip getVipByMemberId(Integer userId) {
		return memberVipMapper.selectListByMemberId(userId);
	}

}
