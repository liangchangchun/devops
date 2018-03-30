package com.stylefeng.guns.modular.backend.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.common.constant.state.FinishType;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

public class MachineProbablityWarpper extends BaseControllerWarpper{
	 public MachineProbablityWarpper(Object list) {
	        super(list);
	 }

	@Override
	protected void warpTheMap(Map<String, Object> map) {
//	 	Integer id = (Integer) map.get("dollId");
//		map.put("machainCode", ZwwContentFactory.me().getTDoll(id).getMachineCode());
//		map.put("dollName", ZwwContentFactory.me().getTDoll(id).getName());
		Integer modifiedBy = (Integer)map.get("modifiedBy");
		map.put("person", ConstantFactory.me().getUserNameById(modifiedBy));

	}
}
