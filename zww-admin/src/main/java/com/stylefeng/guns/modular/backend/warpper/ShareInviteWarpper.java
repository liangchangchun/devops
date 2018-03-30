package com.stylefeng.guns.modular.backend.warpper;

import java.util.Date;
import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.core.base.enums.GenderType;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.TimeUtil;

public class ShareInviteWarpper extends BaseControllerWarpper  {

	 public ShareInviteWarpper(Object list) {
	        super(list);
	 }
	 
	@Override
	protected void warpTheMap(Map<String, Object> map) {
		String gender = (String) map.get("gender");
		map.put("genderName", GenderType.valueStrOf(gender));
		String userId = String.valueOf(map.get("id"));
		Integer invitedNum = ZwwContentFactory.me().getInvitedNum(userId);
		map.put("inviteNum", invitedNum);
		Date invitedTime = ZwwContentFactory.me().getInvitedTime(userId);
		String invitedTimeValue = invitedTime==null?"未被邀请":TimeUtil.formate(invitedTime);
		map.put("invitedTime", invitedTimeValue);
	}
}
