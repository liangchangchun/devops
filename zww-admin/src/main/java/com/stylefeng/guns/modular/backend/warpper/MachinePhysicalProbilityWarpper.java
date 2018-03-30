package com.stylefeng.guns.modular.backend.warpper;


import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;


public class MachinePhysicalProbilityWarpper extends BaseControllerWarpper {

	 public MachinePhysicalProbilityWarpper(Object list) {
	        super(list);
	 }
	 
	@Override
	protected void warpTheMap(Map<String, Object> map) {
		if (map.get("modifiedBy")!=null) {
			Integer modifiedBy = (Integer)map.get("modifiedBy");
			map.put("person", ConstantFactory.me().getUserNameById(modifiedBy));
		}
	}

}
