package com.zww.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zww.api.model.Account;
import com.zww.api.model.Member;
import com.zww.api.model.MemberAddr;
import com.zww.api.service.BaseService;
import com.zww.base.dao.BaseDao;

@RefreshScope
@RestController
public class BaseController implements BaseService {
	
	@Autowired
	BaseDao baseDao;

	@Override
	public Boolean isWorker(@RequestParam(value = "memberId") Integer memberId) {
		  if (memberId == null) {
	            return false;
	        }
	        Boolean worker = baseDao.isWorker(memberId);
	        if (worker == null) {
	            return false;
	        }
	        return worker;
	}

	@Override
	public Member selectMemberById(@RequestParam(value = "memberId") Integer memberId) {
		return baseDao.selectMemberById(memberId);
	}

	@Override
	public MemberAddr selectMemberAddrByPrimaryKey(@RequestParam(value = "addrId") Integer addrId) {
		return baseDao.selectMemberAddrByPrimaryKey(addrId);
	}

	@Override
	public MemberAddr selectDefaultAddr(@RequestParam(value = "memberId") Integer memberId) {
		return baseDao.selectDefaultAddr(memberId);
	}

	@Override
	public Boolean isVIP(Integer memberId) {
	      if (memberId == null) {
	            return false;
	        }
	        Integer payNumber = baseDao.selectPayNumberByMemberId(memberId);
	        if (payNumber != null && payNumber > 0) {
	            return true;
	        }
	        return false;
	}

	@Override
	public Member selectMemberByMemberId(String memberId) {
		return baseDao.selectMemberByMemberId(memberId);
	}

	@Override
	public Account selectAccountById(Integer memberId) {
		return baseDao.selectAccountById(memberId);
	}

	@Override
	public void updateBitStatesById(Integer id, long bitState) {
		baseDao.updateBitStatesById(id, bitState);
	}

	@Override
	public void updateMemberSeeksCardState(Account account) {
		baseDao.updateMemberSeeksCardState(account);
	}

	@Override
	public void updateMemberMonthCardState(Account account) {
		baseDao.updateMemberMonthCardState(account);
	}

}
