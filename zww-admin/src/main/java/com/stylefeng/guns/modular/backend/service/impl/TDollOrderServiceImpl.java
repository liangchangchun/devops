package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.dao.*;
import com.stylefeng.guns.common.persistence.model.*;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.StringUtils;
import com.stylefeng.guns.core.util.TimeUtil;
import com.stylefeng.guns.modular.backend.service.ITDollOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
 * @author konghuanhuan
 * @since 2018-01-30
 */
@Service
public class TDollOrderServiceImpl extends ServiceImpl<TDollOrderMapper, TDollOrder> implements ITDollOrderService {

    @Autowired
    private TDollOrderMapper tDollOrderMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TDollOrderItemMapper tDollOrderItemMapper;

    @Autowired
    private TDollMapper tDollMapper;


    @Autowired
    private MemberChargeHistoryMapper memberChargeHistoryMapper;

    @Autowired
    private TMemberAddrMapper tMemberAddrMapper;

    @Autowired
    private TSystemPrefMapper tSystemPrefMapper;

    @Autowired
    private DollInfoMapper dollInfoMapper;


    //待发货订单
    @Override
    public List<Map<String, Object>> selectTDollOrder(Page<TDollOrder> page, String memberId, String phone) {
        return tDollOrderMapper.selectTDollOrder(page,memberId,phone);
    }

    //已发货订单
    @Override
    public List<Map<String, Object>> selectTDollOrderOut(Page<TDollOrder> page, String memberId, String phone) {
        return tDollOrderMapper.selectTDollOrderOut(page,memberId,phone);
    }

    //按订单编号查询
    @Override
    public TDollOrder selectByorderNum(String orderNum) {
        return tDollOrderMapper.selectByOrderNum(orderNum);
    }

    //寄存娃娃违规返币
    @Override
    public boolean dollBackCoins(Integer tDollOrderId,String memberId) {
        //根据memberId查询
        Member member = memberMapper.selectIdByMemberId(memberId);
        //查询金币信息
        Account account = accountMapper.selectById(member.getId());
        //查询订单信息
        TDollOrder tDollOrder = tDollOrderMapper.selectById(tDollOrderId);
        //查询订单关联
//        TDollOrderItem tDollOrderItem = tDollOrderItemMapper.selectByOrderId(tDollOrder.getId());
        //查询娃娃名称
//        TDoll tDoll = tDollMapper.selectById(tDollOrderItem.getDollId());
        //生成消费记录 返娃娃币
        MemberChargeHistory chargeRecord = new MemberChargeHistory();
        chargeRecord.setChargeDate(new Date());
        chargeRecord.setChargeMethod("违规返币");
        chargeRecord.setCoins(account.getCoins()==null?0:account.getCoins());
        chargeRecord.setCoinsSum(tDollOrder.getDollRedeemCoins()==null?0:tDollOrder.getDollRedeemCoins());
//        chargeRecord.setDollId(tDollOrderItem.getDollId());
        chargeRecord.setMemberId(account.getId());
        chargeRecord.setType("income");
        memberChargeHistoryMapper.updateMemberCount(chargeRecord);
        memberChargeHistoryMapper.insertChargeHistory(chargeRecord);

        tDollOrder.setStatus("已兑换");
        tDollOrder.setModifiedDate(new Date());
        tDollOrder.setModifiedBy(ShiroKit.getUser().getId());
        return retBool(tDollOrderMapper.updateById(tDollOrder));

    }


    //添加娃娃
    @Override
    public boolean backDoll(TDollOrder tDollOrder) {
        //根据memberId查询
        Member member = memberMapper.selectIdByMemberId(tDollOrder.getMemberIDs());
        TMemberAddr memberAddr = tMemberAddrMapper.selectDefaultAddr(member.getId());
        TSystemPref systemPref = tSystemPrefMapper.selectByPrimaryKey("DOLL_STOCK_DAYS");
        TSystemPref deliverCoins = tSystemPrefMapper.selectByPrimaryKey("DELIVERY_COINS");
        Integer deliverCoin = Integer.valueOf(deliverCoins.getValue());
        Integer validDate = Integer.valueOf(systemPref.getValue());
        //通过娃娃编号查询tdoll
        DollInfo dollInfo = dollInfoMapper.selectByDollCode(tDollOrder.getDollCodes());
        TDollOrderItem dollOrderItem = new TDollOrderItem();
        String orderNum = StringUtils.getOrderNumber();
        while (tDollOrderMapper.selectByOrderNum(orderNum) != null) {
            orderNum = StringUtils.getOrderNumber();
        }
        if (memberAddr != null) {
            tDollOrder.setAddressId(memberAddr.getId());
        }
        if (deliverCoin != null) {
            tDollOrder.setDeliverCoins(deliverCoin);
        }
        tDollOrder.setOrderNumber(orderNum);
        tDollOrder.setOrderDate(new Date());
        tDollOrder.setOrderBy(member.getId());
        tDollOrder.setModifiedDate(new Date());
        tDollOrder.setModifiedBy(ShiroKit.getUser().getId());
        tDollOrder.setStatus("寄存中");
        if(dollInfo != null){
            tDollOrder.setDollRedeemCoins(dollInfo.getRedeemCoins()==null?0:dollInfo.getRedeemCoins());
        }else {
            tDollOrder.setDollRedeemCoins(0);
        }
        tDollOrder.setStockValidDate(TimeUtil.plusDay(validDate));
        tDollOrderMapper.insert(tDollOrder);

        dollOrderItem.setOrderId(tDollOrder.getId());
        dollOrderItem.setDollId(1);
        dollOrderItem.setQuantity(tDollOrder.getQuantity());
        dollOrderItem.setCreatedDate(new Date());
        dollOrderItem.setDollCode(tDollOrder.getDollCodes());
        return retBool(tDollOrderItemMapper.insert(dollOrderItem));
    }
}
