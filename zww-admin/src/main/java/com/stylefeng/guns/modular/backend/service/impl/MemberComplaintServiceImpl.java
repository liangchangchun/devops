package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.dao.*;
import com.stylefeng.guns.common.persistence.model.*;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.core.util.TimeUtil;
import com.stylefeng.guns.modular.backend.service.IMemberComplaintService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.backend.warpper.TDollWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
@Service
public class MemberComplaintServiceImpl extends ServiceImpl<MemberComplaintMapper, MemberComplaint> implements IMemberComplaintService {

   @Autowired
   private MemberComplaintMapper memberComplaintMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TDollMapper tDollMapper;

    @Autowired
    private MemberChargeHistoryMapper memberChargeHistoryMapper;

    @Autowired
    private TMemberAddrMapper tMemberAddrMapper;

    @Autowired
    private TDollOrderMapper tDollOrderMapper;

    @Autowired
    private TDollOrderItemMapper tDollOrderItemMapper;

    @Autowired
    private TSystemPrefMapper tSystemPrefMapper;

    @Override
    public List<Map<String, Object>> selectMemberComplain(Page<MemberComplaint> page, String memberID, String complaintChannel,String catchStates,Double vipGroup) {
        return memberComplaintMapper.selectMemberComplain(page, memberID,complaintChannel,catchStates,vipGroup);
    }

    @Override
    public List<Map<String, Object>> selectMemberComplainDone(Page<MemberComplaint> page, String memberID, String complaintChannel,String catchStates,Integer checkState) {
        return memberComplaintMapper.selectMemberComplainDone(page, memberID,complaintChannel,catchStates,checkState);
    }

    @Override
    public boolean complaintBack(MemberComplaint memberComplaint, Integer backStatus) {
        //修改时间
//        Date dates = new Date();
        //查询申诉列表
        MemberComplaint memberComplaintAll = memberComplaint.selectById(memberComplaint.getId());

        if (backStatus == -1) {
            memberComplaint.setCheckState(backStatus);
            memberComplaint.setModifyDate(new Date());
            memberComplaint.setModifyBy(ShiroKit.getUser().getId());
            return retBool(memberComplaintMapper.updateById(memberComplaint));
        } else if (backStatus == 1) {

            if (memberComplaintAll.getCheckState() == 0) {
                Account account = accountMapper.selectById(memberComplaintAll.getMemberId());
                TDoll doll = tDollMapper.selectById(memberComplaintAll.getDollId());
                if(doll != null){
                    if(doll.getMachineType() == 2){
                        //生成消费记录 返钻石
                        MemberChargeHistory chargeRecord = new MemberChargeHistory();
                        chargeRecord.setChargeDate(new Date());
                        chargeRecord.setChargeMethod("申诉返钻石(" + doll.getName() + ")");
                        chargeRecord.setCoins(account.getCoins());
                        chargeRecord.setCoinsSum(0);
                        chargeRecord.setSuperTicket(account.getSuperTicket());
                        chargeRecord.setSuperTicketSum(doll.getPrice());
                        chargeRecord.setDollId(doll.getId());
                        chargeRecord.setMemberId(account.getId());
                        chargeRecord.setType("sincome");
                        memberChargeHistoryMapper.updateMemberCount(chargeRecord);
                        memberChargeHistoryMapper.insertChargeHistory(chargeRecord);
                    }else {
                        //生成消费记录 返娃娃币
                        MemberChargeHistory chargeRecord = new MemberChargeHistory();
                        chargeRecord.setChargeDate(new Date());
                        chargeRecord.setChargeMethod("申诉返币(" + doll.getName() + ")");
                        chargeRecord.setCoins(account.getCoins());
                        chargeRecord.setCoinsSum(doll.getPrice());
                        chargeRecord.setDollId(doll.getId());
                        chargeRecord.setMemberId(account.getId());
                        chargeRecord.setType("income");
                        memberChargeHistoryMapper.updateMemberCount(chargeRecord);
                        memberChargeHistoryMapper.insertChargeHistory(chargeRecord);
                    }
                }
            }
            memberComplaint.setCheckState(backStatus);
            memberComplaint.setModifyDate(new Date());
            memberComplaint.setModifyBy(ShiroKit.getUser().getId());
            return retBool(memberComplaintMapper.updateById(memberComplaint));

        } else if (backStatus == 2) {

            if (memberComplaintAll.getCheckState() == 0) {
                TMemberAddr memberAddr = tMemberAddrMapper.selectDefaultAddr(memberComplaintAll.getMemberId());
                TSystemPref systemPref = tSystemPrefMapper.selectByPrimaryKey("DOLL_STOCK_DAYS");
                TSystemPref deliverCoins = tSystemPrefMapper.selectByPrimaryKey("DELIVERY_COINS");
                Integer deliverCoin = Integer.valueOf(deliverCoins.getValue());
                Integer validDate = Integer.valueOf(systemPref.getValue());
                TDollOrder dollOrder = new TDollOrder();
                TDoll doll = tDollMapper.selectById(memberComplaintAll.getDollId());
                TDollOrderItem dollOrderItem = new TDollOrderItem();
                String orderNum = StringUtils.getOrderNumber();
                while (tDollOrderMapper.selectByOrderNum(orderNum) != null) {
                    orderNum = StringUtils.getOrderNumber();
                }
                if (memberAddr != null) {
                    dollOrder.setAddressId(memberAddr.getId());
                }
                if (deliverCoin != null) {
                    dollOrder.setDeliverCoins(deliverCoin);
                }
                dollOrder.setOrderNumber(orderNum);
                dollOrder.setOrderDate(new Date());
                dollOrder.setOrderBy(memberComplaintAll.getMemberId());
                dollOrder.setModifiedDate(new Date());
                dollOrder.setModifiedBy(memberComplaintAll.getMemberId());
                dollOrder.setStatus("寄存中");
                dollOrder.setDollRedeemCoins(doll.getRedeemCoins());
                if ("1".equals(String.valueOf(doll.getMachineType()))) {//练习房 直接兑换
                    dollOrder.setStatus("已兑换");
                    dollOrder.setDeliverCoins(doll.getRedeemCoins());
                    MemberChargeHistory charge = new MemberChargeHistory();
                    Account account = accountMapper.selectById(memberComplaintAll.getMemberId());
                    charge.setMemberId(memberComplaintAll.getMemberId());
                    charge.setCoins(account.getCoins());
                    charge.setCoinsSum(doll.getRedeemCoins());//练习房兑换奖励
                    charge.setChargeDate(new Date());
                    charge.setType("income");
                    charge.setDollId(doll.getId());
                    charge.setChargeMethod("由<练习房申诉," + doll.getDollID() + ">兑换获取");
                    charge.setChargeDate(new Date());
                    memberChargeHistoryMapper.updateMemberCount(charge);
                    memberChargeHistoryMapper.insertChargeHistory(charge);
                }
                dollOrder.setStockValidDate(TimeUtil.plusDay(validDate));
                tDollOrderMapper.insert(dollOrder);

                dollOrderItem.setOrderId(dollOrder.getId());
                dollOrderItem.setDollId(doll.getId());
                dollOrderItem.setQuantity(1);
                dollOrderItem.setCreatedDate(new Date());
                dollOrderItem.setDollCode(doll.getDollID());
                tDollOrderItemMapper.insert(dollOrderItem);
                tDollMapper.updateById(doll);
            }
            memberComplaint.setCheckState(backStatus);
            memberComplaint.setModifyDate(new Date());
            memberComplaint.setModifyBy(ShiroKit.getUser().getId());
            return retBool(memberComplaintMapper.updateById(memberComplaint));
        } else {
            memberComplaint.setCheckState(backStatus);
            memberComplaint.setModifyDate(new Date());
            memberComplaint.setModifyBy(ShiroKit.getUser().getId());
            return retBool(memberComplaintMapper.updateById(memberComplaint));
        }
    }
}
