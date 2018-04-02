package com.stylefeng.guns.modular.backend.warpper;

import java.util.*;

import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.common.persistence.dao.MemberVipMapper;
import com.stylefeng.guns.common.persistence.model.Account;
import com.stylefeng.guns.common.persistence.model.MemberVip;
import com.stylefeng.guns.core.base.enums.GenderType;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberWarpper extends BaseControllerWarpper {




	public MemberWarpper(Object list) {
	        super(list);
	 }
	 
	@Override
	protected void warpTheMap(Map<String, Object> map) {
		String gender = (String) map.get("gender");
		map.put("genderName", GenderType.valueStrOf(gender));
		Integer userId = (Integer)map.get("id");
		Account account = ZwwContentFactory.me().getAccountById(userId);
		if(account==null) {
			map.put("Acoins", 0);
			map.put("superTicket", 0);
		} else {
		Integer acoins =account.getCoins();
		Integer superTicket =account.getSuperTicket();
			acoins = acoins==null?0:acoins;
			superTicket = superTicket==null?0:superTicket;
		map.put("Acoins", acoins);
		map.put("superTicket", superTicket);
		}


		//vip等级划分
		MemberVip memberVip = ZwwContentFactory.me().getVipByMemberId(userId);
		if(memberVip == null){
			map.put("vipGroup","非vip");
		} else {
			map.put("vipGroup",memberVip.getName());
		}
	}

}
