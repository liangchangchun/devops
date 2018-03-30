package com.zww.api.service;


import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.hystrix.ChargeServiceHystrix;
import com.zww.api.model.Charge;
import com.zww.api.model.DollOrder;
import com.zww.common.ResultMap;


/**
 * @author lgq Version: 1.0 Date: 2017/9/22 Description: 用户Service接口类.
 *         Copyright (c) 2017 mwan. All rights reserved.
 */
@FeignClient(value = "base-service" , fallback = ChargeServiceHystrix.class)
public interface ChargeService {

    /**
     * 获取规则
     */
	@RequestMapping(value = "/getChargeRules" ,method = RequestMethod.POST)
    Charge getChargeRules(@RequestParam(value = "chargePrice") Double chargePrice);
	
	@RequestMapping(value = "/insertChargeHistory" ,method = RequestMethod.POST)
    Integer insertChargeHistory(@RequestParam(value = "charge") Charge charge);
    
	@RequestMapping(value = "/updateMemberCount" ,method = RequestMethod.POST)
	Integer updateMemberCount(@RequestParam(value = "charge") Charge charge);

	@RequestMapping(value = "/insertChargeHistory2" ,method = RequestMethod.POST)
    Integer insertChargeHistory(Charge charge, Integer memberId, Integer[] orderIds);

	@RequestMapping(value = "/getChargeHistory" ,method = RequestMethod.POST)
    List<Charge> getChargeHistory(Integer memberId);

	@RequestMapping(value = "/changeCount" ,method = RequestMethod.POST)
	Integer changeCount(List<DollOrder> dollOrderList);

	@RequestMapping(value = "/inviteChargeFirst" ,method = RequestMethod.POST)
    void inviteChargeFirst(Integer memberId);

	@RequestMapping(value = "/getSuccessfulRechargeRecords" ,method = RequestMethod.POST)
    ResultMap getSuccessfulRechargeRecords(Integer memberId, String mchOrderNo);

	@RequestMapping(value = "/invite" ,method = RequestMethod.POST)
    ResultMap invite(Integer memberId, String inviteCode);

    /**
     * 展示邀请人
     *
     * @param memberId
     * @return
     */
	@RequestMapping(value = "/whoInvite" ,method = RequestMethod.POST)
    ResultMap whoInvite(Integer memberId);

    /**
     * 展示邀请人数
     *
     * @param memberId
     * @return
     */
	@RequestMapping(value = "/howInvite" ,method = RequestMethod.POST)
    ResultMap howInvite(Integer memberId);

	@RequestMapping(value = "/inviteOne" ,method = RequestMethod.POST)
	Integer inviteOne(Integer id, String channel);
}








