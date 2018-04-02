package com.stylefeng.guns.modular.backend.warpper;


import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.common.persistence.model.MemberVip;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;


public class MemberComplainWarpper extends BaseControllerWarpper {

	 public MemberComplainWarpper(Object list) {
	        super(list);
	 }
	 
	@Override
	protected void warpTheMap(Map<String, Object> map) {
		Integer modifiedBys = (Integer)map.get("modifyBy");
		if (map.get("modifyBy")!=null) {
			Integer modifiedBy = (Integer)map.get("modifyBy");
			map.put("person", ConstantFactory.me().getUserNameById(modifiedBy));
		}


		//vip等级划分
		Integer userid = (Integer) map.get("memberId");
		MemberVip memberVip = ZwwContentFactory.me().getVipByMemberId(userid);
		if(memberVip == null){
			map.put("vipGroup","非vip");
		} else {
			map.put("vipGroup",memberVip.getName());
		}
	}

	

}
