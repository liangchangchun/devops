package com.zww.api.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zww.api.model.Charge;
import com.zww.api.model.DollOrder;
import com.zww.api.service.ChargeService;
import com.zww.common.ResultMap;

@Component
public class ChargeServiceHystrix implements ChargeService {

	@Override
	public Charge getChargeRules(Double chargePrice) {
		return null;
	}

	@Override
	public Integer insertChargeHistory(Charge charge) {
		return null;
	}

	@Override
	public Integer insertChargeHistory(Charge charge, Integer memberId, Integer[] dollId) {
		return null;
	}

	@Override
	public List<Charge> getChargeHistory(Integer memberId) {
		return null;
	}

	@Override
	public Integer changeCount(List<DollOrder> dollOrderList) {
		return null;
	}

	@Override
	public void inviteChargeFirst(Integer memberId) {
		
	}

	@Override
	public ResultMap getSuccessfulRechargeRecords(Integer memberId, String mchOrderNo) {
		return null;
	}

	@Override
	public ResultMap invite(Integer memberId, String inviteCode) {
		return null;
	}

	@Override
	public ResultMap whoInvite(Integer memberId) {
		return null;
	}

	@Override
	public ResultMap howInvite(Integer memberId) {
		return null;
	}

	@Override
	public Integer inviteOne(Integer id, String channel) {
		return null;
	}

	@Override
	public Integer updateMemberCount(Charge charge) {
		// TODO Auto-generated method stub
		return null;
	}


}
