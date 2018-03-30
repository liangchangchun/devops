package com.stylefeng.guns.modular.backend.warpper;

import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.common.constant.state.FinishType;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

public class ChargeOrderWarpper extends BaseControllerWarpper{
	 public ChargeOrderWarpper(Object list) {
	        super(list);
	 }

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		Integer userId = (Integer) map.get("memberId");
		map.put("memberIDs", ZwwContentFactory.me().getMemberId(userId));
		Integer chargeState = (Integer) map.get("chargeState");
		map.put("chargeStates", FinishType.valueStrOf(chargeState));

	 }
}
