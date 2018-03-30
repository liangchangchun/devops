package com.zww.base.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import com.zww.api.model.Account;
import com.zww.api.model.Charge;
import com.zww.api.model.Doll;
import com.zww.api.model.DollOrder;
import com.zww.api.model.DollOrderItem;
import com.zww.api.model.Member;
import com.zww.api.model.RiskManagement;
import com.zww.api.model.ShareInvite;
import com.zww.api.model.SystemPref;
import com.zww.api.service.ChargeService;
import com.zww.api.util.StringUtils;
import com.zww.api.util.TimeUtil;
import com.zww.base.dao.BaseDao;
import com.zww.base.dao.ChargeDao;
import com.zww.base.dao.SystemPrefDao;
import com.zww.common.Enviroment;
import com.zww.common.ResultMap;

@RefreshScope
@RestController
public class ChargeController implements ChargeService{
	
	@Autowired
    private ChargeDao chargeDao;
    @Autowired
    private SystemPrefDao systemPrefDao;
    @Autowired
    private BaseDao baseDao;

	@Override
	public Charge getChargeRules(Double chargePrice) {
		 // TODO Auto-generated method stub
        //logger.info("getChargeRules参数chargePrice:{}", chargePrice);
        Charge charge = chargeDao.getChargeRules(chargePrice);
        //logger.info("返回charge:{}", charge);
        return charge;
	}

	@Override
	public Integer insertChargeHistory(Charge charge) {
		try {
            // charge.setCoinsSum(charge.getCoinsSum()+charge.getCoins());
            //logger.info("insertChargeHistory参数charge:{}", charge);
            charge.setChargeDate(TimeUtil.getTime());
            //if (charge.getDollId() == null) {
            //	charge.setChargeMethod("现金充值");
            //}
            chargeDao.updateMemberCount(charge);
            Integer result;
            if (charge.getSuperTicketSum() == null) {
                charge.setSuperTicketSum(0);
            }
            if (charge.getCoinsSum() == null) {
                charge.setCoinsSum(0);
            }
            if (charge.getSuperTicketSum() <= 0 && charge.getCoinsSum() > 0) {//普通金币礼包记录
                result = chargeDao.insertChargeHistory(charge);
                //logger.info("返回result:{}", result);
                return result;
            }
            if (charge.getSuperTicketSum() > 0 && charge.getCoinsSum() <= 0) {//普通纯钻石礼包记录
                charge.setCoinsSum(charge.getSuperTicketSum());
                charge.setCoins(charge.getSuperTicket());
                charge.setType("s" + charge.getType());
                result = chargeDao.insertChargeHistory(charge);
                //logger.info("返回result:{}", result);
                return result;
            }
            if (charge.getSuperTicketSum() > 0 && charge.getCoinsSum() > 0) {//混合礼包记录
                chargeDao.insertChargeHistory(charge);
                charge.setCoinsSum(charge.getSuperTicketSum());
                charge.setCoins(charge.getSuperTicket());
                charge.setType("s" + charge.getType());
                result = chargeDao.insertChargeHistory(charge);
                //logger.info("返回result:{}", result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	 public Integer insertChargeHistory(Charge charge, Integer memberId, Integer[] orderIds) {
        //logger.info("insertChargeHistory参数charge:{},memberId:{},orderIds:{}", charge, memberId, orderIds);
        List<DollOrder> list = new ArrayList<>();
       /* for (int orderId : orderIds) {
            DollOrder dollOrder = dollOrderService.selectByPrimaryKey(orderId);
            if ("寄存中".equals(dollOrder.getStatus())) {//娃娃兑换bug  筛选兑换的娃娃
                dollOrder.setStatus("已兑换");
                dollOrder.setModifiedDate(TimeUtil.getTime());
                dollOrder.setModifiedBy(dollOrder.getOrderBy());
                dollOrderService.updateByPrimaryKeySelective(dollOrder);
                sumCoins += dollOrder.getDollRedeemCoins();
                *//*Map<String, DollOrder> dollMap = new HashMap<>();
                dollMap.put("dollOrder", dollOrder);*//*
                list.add(dollOrder);

                *//*List<DollOrderItem> orderItem = dollOrder.getOrderItems();
                for (DollOrderItem dollOrderItem : orderItem) {
                    Map<String, DollOrderItem> dollMap = new HashMap<>();
                    dollMap.put("dollItem", dollOrderItem);
                    list.add(dollMap);
                }*//*
            }
        }*/
        Integer sumCoins = 0;
        Integer result = 0;
        List<DollOrder> dollOrders = baseDao.selectDollOrderListByIds(orderIds);
        for (DollOrder dollOrder : dollOrders) {
            if ("寄存中".equals(dollOrder.getStatus())) {//娃娃兑换bug  筛选兑换的娃娃
                dollOrder.setStatus("已兑换");
                dollOrder.setModifiedDate(TimeUtil.getTime());
                dollOrder.setModifiedBy(dollOrder.getOrderBy());
                //dollOrderService.updateByPrimaryKeySelective(dollOrder);
                baseDao.updateDollOrderByIdSelective(dollOrder);
                sumCoins += dollOrder.getDollRedeemCoins();
                /*Map<String, DollOrder> dollMap = new HashMap<>();
                dollMap.put("dollOrder", dollOrder);*/
                list.add(dollOrder);

                /*List<DollOrderItem> orderItem = dollOrder.getOrderItems();
                for (DollOrderItem dollOrderItem : orderItem) {
                    Map<String, DollOrderItem> dollMap = new HashMap<>();
                    dollMap.put("dollItem", dollOrderItem);
                    list.add(dollMap);
                }*/
            }
        }
        if (list.size() > 0) {
            for (DollOrder dollOrder : list) {
                Integer dollId = dollOrder.getOrderItems().getDoll().getId();
                //Doll doll = dollService.selectByPrimaryKey(dollId);
                Doll doll = baseDao.selectDollById(dollId);
                //Account account = accountService.select(memberId);
                Account account = baseDao.selectAccountById(memberId);
                charge.setMemberId(memberId);
                charge.setCoins(account.getCoins());
                //charge.setCoinsSum(doll.getRedeemCoins() + member.getCoins());
                charge.setCoinsSum(dollOrder.getDollRedeemCoins());
                charge.setChargeDate(TimeUtil.getTime());
                charge.setType("income");
                charge.setDollId(doll.getId());
                charge.setChargeMethod("由<" + doll.getName() + ">兑换获取");

                //charge.setCoins(doll.getRedeemCoins());
                // charge.setCoinsSum(charge.getCoinsSum()+charge.getCoins());
                charge.setChargeDate(TimeUtil.getTime());
                //	if (charge.getDollId() == null) {
                //		charge.setChargeMethod("现金充值");
                //	}
                chargeDao.updateMemberCount(charge);
                result = chargeDao.insertChargeHistory(charge);
            }
        }
        //logger.info("返回result:{}", result);
        if (result != 0) {
            //logger.info("返回CoinsSum:{}", sumCoins);
            return sumCoins;
        } else {
            return 0;
        }
    }

	@Override
	public List<Charge> getChargeHistory(Integer memberId) {
		  // TODO Auto-generated method stub
        //logger.info("getChargeHistory 参数:{}", memberId);
        return chargeDao.getChargeHistory(memberId);
	}

	@Override
	public Integer changeCount(List<DollOrder> dollOrderList)  {
		 //logger.info("changeCount 参数:{}", dollOrderList);
	        Charge charge = new Charge();
	        DollOrder dOrder = new DollOrder();
	        List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
	        for (DollOrder dollOrder : dollOrderList) {
	            /*List<DollOrderItem> orderItem = dollOrder.getOrderItems();
	            for (DollOrderItem dollOrderItem : orderItem) {
	                Map<String, Integer> dollMap = new HashMap<String, Integer>();
	                dollMap.put("orderId", dollOrder.getId());
	                dollMap.put("memberId", dollOrder.getOrderBy());
	                dollMap.put("dollId", dollOrderItem.getDoll().getId());
	                dollOrderItem.getDoll().getId();
	                list.add(dollMap);
	            }*/
	            DollOrderItem orderItem = dollOrder.getOrderItems();
	            Map<String, Integer> dollMap = new HashMap<String, Integer>();
	            dollMap.put("orderId", dollOrder.getId());
	            dollMap.put("memberId", dollOrder.getOrderBy());
	            dollMap.put("dollId", orderItem.getDoll().getId());
	            list.add(dollMap);

	        }
	        Integer result = 0;
	        for (Map<String, Integer> map : list) {
	            dOrder.setStatus("已兑换");
	            dOrder.setId(map.get("orderId"));
	            //result = dollOrderService.updateByPrimaryKeySelective(dOrder);
	            result = baseDao.updateDollOrderByIdSelective(dOrder);
	            Integer dollId = map.get("dollId");
	            //Doll doll = dollService.selectByPrimaryKey(dollId);
	            Doll doll = baseDao.selectDollById(dollId);
	           // Member member = memberService.selectById(map.get("memberId"));
	            Member member = baseDao.selectMemberById(map.get("memberId"));
	            charge.setMemberId(map.get("memberId"));
	            //charge.setCoinsSum(doll.getRedeemCoins() + member.getCoins());
	            charge.setCoinsSum(doll.getRedeemCoins());
	            charge.setChargeDate(TimeUtil.getTime());
	            charge.setType("income");
	            charge.setDollId(doll.getId());
	            charge.setChargeMethod("由<" + doll.getName() + ">兑换获取");
	            //charge.setCoins(doll.getRedeemCoins());
	            charge.setCoins(member.getAccount().getCoins());
	            // charge.setCoinsSum(charge.getCoinsSum()+charge.getCoins());
	            charge.setChargeDate(TimeUtil.getTime());
	            //	if (charge.getDollId() == null) {
	            //		charge.setChargeMethod("现金充值");
	            //	}
	            chargeDao.updateMemberCount(charge);
	            result = chargeDao.insertChargeHistory(charge);
	        }
	        //logger.info("resul:{}", result);
	        return result;
	}

	@Override
	public void inviteChargeFirst(Integer memberId) {
		 Map<String, Integer> parameterMap = new HashMap<String, Integer>();
	        parameterMap.put("memberId", memberId);
	        parameterMap.put("result", 0);
	        chargeDao.inviteChargeFirst(parameterMap);
	}

	@Override
	public ResultMap getSuccessfulRechargeRecords(Integer memberId, String mchOrderNo) {
		 Charge charge = null;
	        int i = 0;
	        while (i < 10) {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            i += 1;
	            if (chargeDao.getChargeByMchOrderNo(mchOrderNo) != null) {
	                //查询成功
	                Map<String, Object> map = new HashMap<>();
	                //Account account = accountService.select(memberId);
	                Account account = baseDao.selectAccountById(memberId);
	                map.put("coins", account.getCoins());
	                map.put("superTicket", account.getSuperTicket());
	                return new ResultMap(Enviroment.PAY_SUCCESS, map);
	            }
	        }
	        //查询失败
	        return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.RETURN_MESSAGE_TIMEOUT);
	}
	
	private boolean isOneIMEI(Integer memberId, Integer memberId2) {
        if (memberId.equals(memberId2)) {
            return true;
        }
        //RiskManagement byMemberId = riskManagementDao.selectByMemberId(memberId);
        RiskManagement byMemberId = baseDao.selectRiskManagementById(memberId);
        List<String> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(byMemberId.getIMEI1()) && !"IMEI".equals(byMemberId.getIMEI1())) {
            list.add(byMemberId.getIMEI1());
        }
        if (StringUtils.isNotEmpty(byMemberId.getIMEI2()) && !"IMEI".equals(byMemberId.getIMEI2())) {
            list.add(byMemberId.getIMEI2());
        }
        if (StringUtils.isNotEmpty(byMemberId.getIMEI3()) && !"IMEI".equals(byMemberId.getIMEI3())) {
            list.add(byMemberId.getIMEI3());
        }
        //RiskManagement byMemberId2 = riskManagementDao.selectByMemberId(memberId2);
        RiskManagement byMemberId2 = baseDao.selectRiskManagementById(memberId2);
        List<String> list2 = new ArrayList<>();
        if (StringUtils.isNotEmpty(byMemberId2.getIMEI1()) && !"IMEI".equals(byMemberId2.getIMEI1())) {
            list2.add(byMemberId2.getIMEI1());
        }
        if (StringUtils.isNotEmpty(byMemberId2.getIMEI2()) && !"IMEI".equals(byMemberId2.getIMEI2())) {
            list2.add(byMemberId2.getIMEI2());
        }
        if (StringUtils.isNotEmpty(byMemberId2.getIMEI3()) && !"IMEI".equals(byMemberId2.getIMEI3())) {
            list2.add(byMemberId2.getIMEI3());
        }
        if (list.size() == 0 || list2.size() == 0) {
            return false;
        }
        for (String s : list2) {
            System.out.println(s);
            if (list.contains(s)) {
                return true;
            }
        }
        return false;
    }

	@Override
	public ResultMap invite(Integer memberId, String inviteCode) {
		//被邀请人信息
        //Member member = memberService.selectById(memberId);
        Member member = baseDao.selectMemberById(memberId);
        //验证是否已经被邀请过
        if (member.isInviteFlgWeb() == true) {
            //logger.info("邀请异常=" + Enviroment.RECEIVED_INVITATION_AWARD);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.RECEIVED_INVITATION_AWARD);
        }
        //邀请人信息
        //Member memberInvite = memberService.selectByMemberID(inviteCode);
        Member memberInvite = baseDao.selectMemberByMemberId(inviteCode);
        //查询邀请人是否存在
        if (memberInvite == null) {
            //logger.info("邀请异常=" + Enviroment.INVALID_INVITATION_CODE);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.INVALID_INVITATION_CODE);
        }
        //邀请码是否是自己
        if (memberId == memberInvite.getId()) {
            //logger.info("邀请异常=" + Enviroment.PROHIBIT_INVITING_ONESELF);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.PROHIBIT_INVITING_ONESELF);
        }
        //检测双方设备号
        if (isOneIMEI(memberId,memberInvite.getId())) {
            //logger.info("邀请异常=" + Enviroment.ISONEIMEI_ONESELF);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.ISONEIMEI_ONESELF);
        }
        //生成娃娃币流水
        Charge invicteCharge = new Charge();
        invicteCharge.setCode(Enviroment.CODE_INVITE_BONUS);
        invicteCharge.setInviteMemberId(memberInvite.getId());
        invicteCharge.setMemberId(memberId);
        invicteCharge.setCoins(member.getAccount().getCoins());
        invicteCharge.setType(Enviroment.TYPE_INCOME);
        //邀请奖励
        SystemPref INVITE_BONUS = systemPrefDao.selectByPrimaryKey(Enviroment.CODE_INVITE_BONUS);
        //邀请人上限
        SystemPref INVITE_BONUS_COUNT = systemPrefDao.selectByPrimaryKey(Enviroment.CODE_INVITE_BONUS_COUNT);
        Integer inviteLimit = INVITE_BONUS_COUNT == null ? 0 : Integer.parseInt(INVITE_BONUS_COUNT.getValue());
        //查询邀请次数
        Integer inviteNum = chargeDao.getChargeByInviteCode(inviteCode);
        invicteCharge.setCoinsSum(Integer.valueOf(INVITE_BONUS.getValue()));//邀请获取娃娃币
        if (inviteNum >= inviteLimit && inviteLimit > 0) {
            //邀请人获取赠送币数量
            invicteCharge.setInviteCoinsSum(0);
            //invicteCharge.setChargeMethod("邀请好友奖励达到上限,邀请好友id" + memberService.selectById(invicteCharge.getMemberId()).getMemberID());
            invicteCharge.setChargeMethod("邀请好友奖励达到上限,邀请好友id" + baseDao.selectMemberById(invicteCharge.getMemberId()).getMemberID());
        } else {
            invicteCharge.setInviteCoinsSum(Integer.valueOf(INVITE_BONUS.getValue()));//邀请人获取赠送币数量
            //invicteCharge.setChargeMethod("由邀请好友获取,邀请好友id" + memberService.selectById(invicteCharge.getMemberId()).getMemberID());
            invicteCharge.setChargeMethod("由邀请好友获取,邀请好友id" + baseDao.selectMemberById(invicteCharge.getMemberId()).getMemberID());
        }
        //邀请人获取奖励
        invicteCharge.setChargeDate(TimeUtil.getTime());
        //更新邀请人币数
        chargeDao.updateInviteMemberCount(invicteCharge);
        //生成邀请人记录
        chargeDao.insertInviteChargeHistory(invicteCharge);
        //被邀请人获取奖励
        chargeDao.updateMemberCount(invicteCharge);
        //标记被邀请人被邀请状态
        //memberDao.updateInviteStatus(memberId);
        baseDao.updateInviteStatus(memberId);
        chargeDao.insertChargeHistory(invicteCharge);
        //生成邀请记录
        ShareInvite invite = new ShareInvite();
        invite.setInviteCode(inviteCode);
        invite.setInviteMemberId(String.valueOf(invicteCharge.getInviteMemberId()));
        invite.setInvitedId(String.valueOf(memberId));//被邀请人id
        invite.setCreateDate(new Date());
        invite.setState(0);
        chargeDao.insertInvite(invite);
        //memberDao.updateChannel(memberId, memberInvite.getRegisterChannel(), memberInvite.getId(), baseDao.selectRank(memberInvite.getId()) + 1);
        baseDao.updateChannel(memberId, memberInvite.getRegisterChannel(), memberInvite.getId(), baseDao.selectRank(memberInvite.getId()) + 1);
        //logger.info("邀请成功=" + Enviroment.INVALID_SUCCESS_MESSAGE);
        return new ResultMap(Enviroment.INVALID_SUCCESS_MESSAGE);
	}

	@Override
	public ResultMap whoInvite(Integer memberId) {
		  try {
	            String invite = chargeDao.whoInvite(String.valueOf(memberId));
	            if (StringUtils.isNotEmpty(invite)) {
	                Map<String, String> map = new HashMap<>();
	                map.put("invite", invite);
	                //logger.info("展示邀请人接口成功inviteid=" + invite);
	                return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, map);
	            }
	            //logger.info("展示邀请人接口失败");
	            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, "没有查询到邀请人的邀请码");
	        } catch (Exception e) {
	            //logger.error("展示邀请人失败异常=" + e.getMessage());
	            e.printStackTrace();
	            return new ResultMap(Enviroment.ERROR_CODE, Enviroment.HAVE_ERROR);
	        }
	}

	@Override
	public ResultMap howInvite(Integer memberId) {
		try {
            Integer invitenumber = chargeDao.howInvite(String.valueOf(memberId));
            if (invitenumber != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("inviteNumber", invitenumber);
                //logger.info("展示邀请人接口成功");
                return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE, map);
            }
            //logger.info("展示邀请人接口失败");
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, "没有查询到您的邀请人数");
        } catch (Exception e) {
            //logger.error("展示邀请人失败异常=" + e.getMessage());
            e.printStackTrace();
            return new ResultMap(Enviroment.ERROR_CODE, Enviroment.HAVE_ERROR);
        }
	}
	
	private String verifyChannel(String channel) {
	        List<SystemPref> systemPref = systemPrefDao.selectChannel();
	        for (SystemPref pref : systemPref) {
	            if (pref.getValue().equals(channel)) {
	                return pref.getName();
	            }
	        }
	        return null;
	}

	@Override
	public Integer inviteOne(Integer id, String channel) {
		//channel是否合法
        try {
           // Member member = memberService.selectById(id);
        	 Member member = baseDao.selectMemberById(id);
            String channelName = null;
            channelName = verifyChannel(channel);
            if (StringUtils.isEmpty(channelName)) {
                return 2;
            }
            //生成娃娃币流水
            Charge invicteCharge = new Charge();
            invicteCharge.setCode(Enviroment.CODE_INVITE_BONUS);
            invicteCharge.setInviteMemberId(null);
            invicteCharge.setMemberId(id);
            invicteCharge.setCoins(member.getAccount().getCoins());
            invicteCharge.setType(Enviroment.TYPE_INCOME);
            //邀请奖励
            SystemPref INVITE_BONUS = systemPrefDao.selectByPrimaryKey(Enviroment.CODE_INVITE_BONUS);
            //邀请人上限
            //查询邀请次数
            invicteCharge.setCoinsSum(Integer.valueOf(INVITE_BONUS.getValue()));//邀请获取娃娃币
            invicteCharge.setChargeMethod(channelName + "注册惊喜礼包");
            //邀请人获取奖励
            invicteCharge.setChargeDate(TimeUtil.getTime());
            //被邀请人获取奖励
            chargeDao.updateMemberCount(invicteCharge);
            //标记被邀请人被邀请状态
            //memberDao.updateInviteStatus(id);
            baseDao.updateInviteStatus(id);
            chargeDao.insertChargeHistory(invicteCharge);

            //生成邀请记录
            ShareInvite invite = new ShareInvite();
            invite.setInviteCode(null);
            invite.setInviteMemberId(String.valueOf(invicteCharge.getInviteMemberId()));
            invite.setInvitedId(String.valueOf(id));//被邀请人id
            invite.setCreateDate(new Date());
            invite.setState(0);
            chargeDao.insertInvite(invite);
           // memberDao.updateChannel(id, channel, null, 1);
            baseDao.updateChannel(id, channel, null, 1);
            //logger.info("邀请成功=" + Enviroment.INVALID_SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
	}

	@Override
	public Integer updateMemberCount(Charge charge) {
		return chargeDao.updateMemberCount(charge);
	}

}
