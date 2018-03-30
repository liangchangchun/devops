package com.stylefeng.guns.modular.backend.warpper;


import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;


public class TDollWarpper extends BaseControllerWarpper {

	 public TDollWarpper(Object list) {
	        super(list);
	 }
	 
	@Override
	protected void warpTheMap(Map<String, Object> map) {
		if (map.get("modifiedBy")!=null) {
			Integer modifiedBy = (Integer)map.get("modifiedBy");
			map.put("person",ConstantFactory.me().getUserNameById(modifiedBy));
		}
	}

	

}
