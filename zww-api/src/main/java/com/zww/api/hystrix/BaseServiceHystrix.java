package com.zww.api.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.model.Account;
import com.zww.api.model.Member;
import com.zww.api.model.MemberAddr;
import com.zww.api.service.BaseService;

@Component
public class BaseServiceHystrix implements BaseService{

	@Override
	public Boolean isWorker(@RequestParam(value = "memberId") Integer memberId) {
		return false;
	}
	
	@Override
	public Boolean isVIP(@RequestParam(value = "memberId") Integer memberId) {
		return null;
	}

	@Override
	public Member selectMemberById(Integer memberId) {
		return null;
	}

	@Override
	public MemberAddr selectMemberAddrByPrimaryKey(Integer addrId) {
		return null;
	}

	@Override
	public MemberAddr selectDefaultAddr(Integer memberId) {
		return null;
	}

	@Override
	public Member selectMemberByMemberId(String memberId) {
		return null;
	}

	@Override
	public Account selectAccountById(Integer memberId) {
		return null;
	}

	@Override
	public void updateBitStatesById(Integer id, long bitState) {
		
	}

	@Override
	public void updateMemberSeeksCardState(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMemberMonthCardState(Account account) {
		// TODO Auto-generated method stub
		
	}



}
