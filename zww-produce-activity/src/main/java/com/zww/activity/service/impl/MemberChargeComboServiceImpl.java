package com.zww.activity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zww.activity.dao.MemberChargeComboDao;
import com.zww.activity.service.MemberChargeComboService;
import com.zww.api.model.Account;
import com.zww.api.model.Charge;
import com.zww.api.model.MemberChargeCombo;
import com.zww.api.service.BaseService;
import com.zww.api.service.ChargeService;
import com.zww.api.util.BitStatesUtils;
import com.zww.api.util.TimeUtil;
import com.zww.common.Enviroment;
import com.zww.common.ResultMap;

import java.util.*;

/**
 * Created by SUN on 2018/1/17.
 */
@Service("MemberChargeComboService")
@Transactional
public class MemberChargeComboServiceImpl implements MemberChargeComboService {
    //private static final Logger logger = LoggerFactory.getLogger(MemberChargeComboService.class);

    @Autowired
    private MemberChargeComboDao memberChargeComboDao;
    //@Autowired
    //private AccountService accountService;
    @Autowired
    BaseService baseService;
    @Autowired
    private ChargeService chargeService;


    @Override
    public ResultMap weeksCombo(Integer memberId) {
        //判断用户是否登录：如果登录了就直接签到；如果没登录者提示用户登录
        //Account baseaccount = accountService.select(memberId);
    	Account baseaccount = baseService.selectAccountById(memberId);
        Account account = new Account();
        account.setId(baseaccount.getId());
        account.setBitState(baseaccount.getBitState());
        //检测周卡是否到期
        if (!baseaccount.getWeeksCardEnd()) {
            //logger.info("周卡领取失败=" + Enviroment.WEEKSCARD_END);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.WEEKSCARD_END);
        }
        //检测是否月卡当日已经领取
        if (baseaccount.getTodyWeeksCard()) {
            //logger.info("周卡领取失败=" + Enviroment.HAVE_BEEN_COMBO);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.HAVE_BEEN_COMBO);
        }
        //领取周卡
        MemberChargeCombo combo = memberChargeComboDao.selectMemberEffect(memberId, 2);
        Date currDate = new Date();
        //计算截止时间
        Date limitDate = TimeUtil.plusDay(combo.getChargeDateStart(), combo.getChargeDateLimit());
        //充值当天不赠送,次日赠送  超过了时长包期限不赠送
        if (!TimeUtil.isSameDate(combo.getChargeDateStart(), currDate) && currDate.before(limitDate)) {
            //给购买时长包    添加娃娃币
            if (combo != null &&
                    combo.getMemberState() == 0 &&
                    combo.getGiveTimes() < combo.getChargeDateLimit()) {
                Integer times = combo.getGiveTimes();
                combo.setGiveTimes(times + 1);
                if (times == combo.getChargeDateLimit()) {
                    //赠送完成
                    combo.setMemberState(1);
                    account.removeState(BitStatesUtils.WEEKS_CARD);
                }
                if (!currDate.before(limitDate)) {//逾期失效
                    combo.setMemberState(1);
                    account.removeState(BitStatesUtils.WEEKS_CARD);
                }
                //记录周卡领取时间
                account.setCoins(combo.getCoinsGive());
                account.setWeeksCardState(currDate);
                memberChargeComboDao.giveCoins(combo);
                //accountService.updateById(account);
                //accountService.updateMemberSeeksCardState(account);
                baseService.updateMemberSeeksCardState(account);
               // logger.info("赠送参数combo=" + combo);
                //赠送
                Charge charge = new Charge();
                charge.setMemberId(memberId);
                charge.setCoins(baseaccount.getCoins());
                charge.setCoinsSum(combo.getCoinsGive());
                charge.setType("income");
                charge.setChargeDate(TimeUtil.getTime());
                charge.setChargeMethod(combo.getChargeName() + "领取");
               // logger.info("充值参数charge=" + charge);
                Integer result = chargeService.insertChargeHistory(charge);
               // logger.info("充值结果result=" + result);
                Map<String, Object> map = new HashMap<>();
                map.put("coins", baseaccount.getCoins() + combo.getCoinsGive());
                return new ResultMap(Enviroment.COMBO_IN_SUCCESS, map);
            }
        }
        return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, "充值当天不赠送,次日赠送  超过了时长包期限不赠送");
    }

    @Override
    public ResultMap monthCombo(Integer memberId) {
        //判断用户是否登录：如果登录了就直接签到；如果没登录者提示用户登录
        // Account account = accountService.select(memberId);
        //Account baseaccount = accountService.select(memberId);
    	Account baseaccount = baseService.selectAccountById(memberId);
        Account account = new Account();
        account.setId(baseaccount.getId());
        account.setBitState(baseaccount.getBitState());
        //检测月卡是否到期
        if (!baseaccount.getMonthCardend()) {
            //logger.info("月卡领取失败=" + Enviroment.MCARD_END);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.MCARD_END);
        }
        //检测是否月卡当日已经领取
        if (baseaccount.getTodyMonthCard()) {
            //logger.info("月卡领取失败=" + Enviroment.HAVE_BEEN_COMBO);
            return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, Enviroment.HAVE_BEEN_COMBO);
        }
        //领取周卡
        MemberChargeCombo combo = memberChargeComboDao.selectMemberEffect(memberId, 3);
        Date currDate = new Date();
        //计算截止时间
        Date limitDate = TimeUtil.plusDay(combo.getChargeDateStart(), combo.getChargeDateLimit());
        //充值当天不赠送,次日赠送  超过了时长包期限不赠送
        if (!TimeUtil.isSameDate(combo.getChargeDateStart(), currDate) && currDate.before(limitDate)) {
            //给购买时长包    添加娃娃币
            if (combo != null &&
                    combo.getMemberState() == 0 &&
                    combo.getGiveTimes() < combo.getChargeDateLimit()) {
                Integer times = combo.getGiveTimes();
                combo.setGiveTimes(times + 1);
                if (times == combo.getChargeDateLimit()) {
                    //赠送完成
                    combo.setMemberState(1);
                    account.removeState(BitStatesUtils.MONTH_CARD);
                }
                if (!currDate.before(limitDate)) {//逾期失效
                    combo.setMemberState(1);
                    account.removeState(BitStatesUtils.MONTH_CARD);
                }
                //记录月卡领取时间
                account.setCoins(combo.getCoinsGive());
                account.setMonthCardState(currDate);
                memberChargeComboDao.giveCoins(combo);
                // accountService.updateById(account);
                //accountService.updateMemberMonthCardState(account);
                baseService.updateMemberMonthCardState(account);
                //logger.info("赠送参数combo=" + combo);
                //赠送
                Charge charge = new Charge();
                charge.setMemberId(memberId);
                charge.setCoins(baseaccount.getCoins());
                charge.setCoinsSum(combo.getCoinsGive());
                charge.setType("income");
                charge.setChargeDate(TimeUtil.getTime());
                charge.setChargeMethod(combo.getChargeName() + "领取");
                //logger.info("充值参数charge=" + charge);
                Integer result = chargeService.insertChargeHistory(charge);
                //logger.info("充值结果result=" + result);
                Map<String, Object> map = new HashMap<>();
                map.put("coins", baseaccount.getCoins() + combo.getCoinsGive());
                return new ResultMap(Enviroment.COMBO_IN_SUCCESS, map);
            }
        }
        return new ResultMap(Enviroment.RETURN_UNAUTHORIZED_CODE, "充值当天不赠送,次日赠送  超过了时长包期限不赠送");
    }
}
