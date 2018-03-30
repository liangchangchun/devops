package com.stylefeng.guns.modular.backend.warpper;

import com.stylefeng.guns.common.constant.state.UpperAndLowerShelvesType;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

public class TChargeRulesWarpper extends BaseControllerWarpper {

	 public TChargeRulesWarpper(Object list) {
	        super(list);
	 }
	 
	@Override
	protected void warpTheMap(Map<String, Object> map) {
		Integer rulesStatus = (Integer) map.get("rulesStatus");
		map.put("rulesStatuses", UpperAndLowerShelvesType.valueStrOf(rulesStatus));
	}

}
