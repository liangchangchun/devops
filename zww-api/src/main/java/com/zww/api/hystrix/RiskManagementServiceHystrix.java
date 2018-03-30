package com.zww.api.hystrix;

import org.springframework.stereotype.Component;

import com.zww.api.service.RiskManagementService;

@Component
public class RiskManagementServiceHystrix implements RiskManagementService{

	@Override
	public Integer register(Integer memberId, String imei, String ipAdrress) {
		return null;
	}

	@Override
	public boolean isOneIMEI(Integer memberId, Integer memberId2) {
		return false;
	}

	@Override
	public Integer selectIMEICount(String imei) {
		return null;
	}

	

}
