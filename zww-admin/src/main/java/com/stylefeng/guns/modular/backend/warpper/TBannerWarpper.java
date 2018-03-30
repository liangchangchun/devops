package com.stylefeng.guns.modular.backend.warpper;


import com.stylefeng.guns.common.constant.factory.ZwwContentFactory;
import com.stylefeng.guns.common.persistence.model.TBanner;
import com.stylefeng.guns.common.persistence.model.TChargeRules;
import com.stylefeng.guns.core.base.enums.LinkType;
import com.stylefeng.guns.core.base.warpper.ModelControllerWarpper;

public class TBannerWarpper extends ModelControllerWarpper<TBanner> {

	 public TBannerWarpper(Object list) {
	        super(list);
	 }
	 
	@Override
	protected void warpTheModel(TBanner banner) {
		if (banner!=null) {
		    String	linkType =String.valueOf(banner.getLinkType());
			banner.setLinkTypeName(LinkType.valueStrOf(linkType));
			TChargeRules chargeRule=ZwwContentFactory.me().getTChargeRules(banner.getPayIndex());
			String chargeName = chargeRule==null?"--":chargeRule.getChargeName();
			banner.setPayIndexName(chargeName);
			String qqGroupNum = "".equals(banner.getQqGroupNum())?"--":banner.getQqGroupNum();
			String qqGroupKey = "".equals(banner.getQqGroupKey())?"--":banner.getQqGroupKey();
			banner.setQqGroupNum(qqGroupNum);
			banner.setQqGroupKey(qqGroupKey);
		}
	}

	

}
