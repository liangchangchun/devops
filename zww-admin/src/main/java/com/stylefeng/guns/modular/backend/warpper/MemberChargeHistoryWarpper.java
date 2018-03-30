package com.stylefeng.guns.modular.backend.warpper;

import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

public class MemberChargeHistoryWarpper extends BaseControllerWarpper{
	 public MemberChargeHistoryWarpper(Object list) {
	        super(list);
	 }

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		Integer userId = (Integer) map.get("memberId");
		 map.put("memberIDs", ZwwContentFactory.me().getMemberId(userId));
	}
}
