package com.zww.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.hystrix.BaseServiceHystrix;
import com.zww.api.model.Account;
import com.zww.api.model.Member;
import com.zww.api.model.MemberAddr;

@FeignClient(value = "base-service" , fallback = BaseServiceHystrix.class)
public interface BaseService {

	@RequestMapping(value = "/isWorker" ,method = RequestMethod.POST)
    Boolean isWorker(@RequestParam(value = "memberId") Integer memberId);
	
	@RequestMapping(value = "/isVIP" ,method = RequestMethod.POST)
    Boolean isVIP(@RequestParam(value = "memberId") Integer memberId);
	
	@RequestMapping(value = "/selectMemberById" ,method = RequestMethod.POST)
	Member selectMemberById(@RequestParam(value = "memberId") Integer memberId);
	
	@RequestMapping(value = "/selectMemberByMemberId" ,method = RequestMethod.POST)
	Member selectMemberByMemberId(@RequestParam(value = "memberId") String memberId);
	
	@RequestMapping(value = "/selectAccountById" ,method = RequestMethod.POST)
	Account selectAccountById(@RequestParam(value = "memberId") Integer memberId);
	
	@RequestMapping(value = "/selectMemberAddrByPrimaryKey" ,method = RequestMethod.POST)
	MemberAddr selectMemberAddrByPrimaryKey(@RequestParam(value = "addrId") Integer addrId);
	
	@RequestMapping(value = "/selectDefaultAddr" ,method = RequestMethod.POST)
	MemberAddr selectDefaultAddr(@RequestParam(value = "memberId") Integer memberId);
	
	@RequestMapping(value = "/updateBitStatesById" ,method = RequestMethod.POST)
	void updateBitStatesById(@RequestParam(value = "id") Integer id,@RequestParam(value = "bitState") long bitState);
	
	 //更新 周卡
	@RequestMapping(value = "/updateMemberSeeksCardState" ,method = RequestMethod.POST)
    void updateMemberSeeksCardState(@RequestParam(value = "account") Account account);
    //更新 月卡
	@RequestMapping(value = "/updateMemberMonthCardState" ,method = RequestMethod.POST)
    void updateMemberMonthCardState(@RequestParam(value = "account") Account account);
}
