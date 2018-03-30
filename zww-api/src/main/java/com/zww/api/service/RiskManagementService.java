package com.zww.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.hystrix.RiskManagementServiceHystrix;


/**
 * Created by SUN on 2018/3/2.
 * 用户账户相关服务类接口
 */
@FeignClient(value = "risk-service" , fallback = RiskManagementServiceHystrix.class)
public interface RiskManagementService {

	@RequestMapping(value = "/register" ,method = RequestMethod.POST)
	Integer register(@RequestParam(value = "memberId") Integer memberId,@RequestParam(value = "imei") String imei,@RequestParam(value = "ipAdrress") String ipAdrress);

	@RequestMapping(value = "/isOneIMEI" ,method = RequestMethod.POST)
    boolean isOneIMEI(@RequestParam(value = "memberId") Integer memberId,@RequestParam(value = "memberId2") Integer memberId2);

	@RequestMapping(value = "/selectIMEICount" ,method = RequestMethod.POST)
	Integer selectIMEICount(@RequestParam(value = "imei") String imei);
}