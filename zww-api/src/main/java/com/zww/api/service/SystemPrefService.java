package com.zww.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.hystrix.SystemPrefServiceHystrix;
import com.zww.api.model.SystemPref;

@FeignClient(value = "base-service" , fallback = SystemPrefServiceHystrix.class)
public interface SystemPrefService {
	//获取系统参数服务
	@RequestMapping(value = "/selectByPrimaryKey" ,method = RequestMethod.POST)
	SystemPref selectByPrimaryKey(@RequestParam(value = "code") String code);
	
	@RequestMapping(value = "/selectChannel" ,method = RequestMethod.POST)
	List<SystemPref> selectChannel();
	
	@RequestMapping(value = "/hello")
	public String hello(@RequestParam(value = "code")  String code);
}
