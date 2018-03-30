package com.zww.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zww.api.model.*;
import com.zww.api.service.BaseService;
import com.zww.api.service.ChargeService;
import com.zww.api.service.SystemPrefService;
import com.zww.api.util.StringUtils;
import com.zww.api.util.TimeUtil;
import com.zww.common.Enviroment;
import com.zww.common.ResultMap;
import com.zww.order.dao.DollDao;
import com.zww.order.dao.DollOrderDao;
import com.zww.order.dao.DollOrderGoodsDao;
import com.zww.order.dao.DollOrderItemDao;
import com.zww.order.service.DollOrderService;


/**
 * Author: mwan Version: 1.1 Date: 2017/09/27 Description: 娃娃发货或寄存订单业务接口实现.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
@Service("DollOrderService")
@Transactional
public class DollOrderServiceImpl implements DollOrderService {
    //private static final Logger logger = LoggerFactory.getLogger(DollOrderServiceImpl.class);
    @Autowired
    private DollOrderDao dollOrderDao;
    @Autowired
    private DollOrderItemDao dollOrderItemDao;
    @Autowired
    private DollDao dollDao;
    @Autowired
    DollOrderGoodsDao dollOrderGoodsDao;
    @Autowired
    SystemPrefService systemPrefService;
    //@Autowired
    //MemberAddrDao memberAddrDao;
    @Autowired
    BaseService baseService;
    @Autowired
    ChargeService chargeService;

    @Override
    public List<DollOrder> selectListByOrderIds(Integer[] orderIds) {
        return dollOrderDao.selectListByOrderIds(orderIds);
    }

    @Override
    public DollOrder selectByPrimaryKey(Integer id) {
        return dollOrderDao.selectByPrimaryKey(id);
    }

    @Override
    public List<DollOrder> selectListByPrimaryKey(Integer[] orderIds) {
        return dollOrderDao.selectListByPrimaryKey(orderIds);
    }

    @Override
    public List<DollOrderItem> selectItemsByMemberId(Integer memberId) {
        return dollOrderItemDao.selectByMemberId(memberId);
    }

    @Override
    public List<DollOrderItem> selectItemsByMemberIdOrderStatus(Integer memberId, String orderStatus) {
        return dollOrderItemDao.selectByMemberIdOrderStatus(memberId, orderStatus);
    }

    @Transactional
    public Integer insertOrder(Integer memberId, Integer dollId, Integer dollNum) {
        Doll doll = dollDao.selectByPrimaryKey(dollId);
        /*if ("3".equals(String.valueOf(doll.getMachineType()))) {
            return 1;
        }*/
        //logger.info("insertOrder 参数memberId:{},dollId:{},dollNum:{}", memberId, dollId, dollNum);
        //查询用户默认地址
        MemberAddr memberAddr = baseService.selectDefaultAddr(memberId);
        //logger.info("memberAddr:{}", memberAddr);
        //查询系统寄存箱默认
        SystemPref systemPref = systemPrefService.selectByPrimaryKey("DOLL_STOCK_DAYS");
        SystemPref deliverCoins = systemPrefService.selectByPrimaryKey("DELIVERY_COINS");
        Integer deliverCoin = Integer.valueOf(deliverCoins.getValue());
        //logger.info("systemPref:{}", systemPref);
        Integer validDate = Integer.valueOf(systemPref.getValue());
        //logger.info("validDate:{}", validDate);
        DollOrder dollOrder = new DollOrder();
        DollOrderItem dollOrderItem = new DollOrderItem();
        String orderNum = StringUtils.getOrderNumber();
        while (dollOrderDao.selectByOrderNum(orderNum) != null) {
            orderNum = StringUtils.getOrderNumber();
        }
        if (memberAddr != null) {
            dollOrder.setMemberAddress(memberAddr);
        }
        if (deliverCoin != null) {
            dollOrder.setDeliverCoins(deliverCoin);
        }
        dollOrder.setDollRedeemCoins(doll.getRedeemCoins());
        dollOrder.setOrderNumber(orderNum);
        dollOrder.setOrderDate(TimeUtil.getTime());
        dollOrder.setOrderBy(memberId);
        dollOrder.setStatus("寄存中");
        String machineType = String.valueOf(doll.getMachineType());
        if ("1".equals(machineType) || "3".equals(machineType)) {//练习房 直接兑换
            dollOrder.setStatus("已兑换");
            //SystemPref practiceRoom = systemPrefDao.selectByPrimaryKey("PRACTICE_ROOM_BONUS");
            //String procticeCoins = practiceRoom==null?"0":practiceRoom.getValue();
            //dollOrder.setDeliverCoins(Integer.parseInt(procticeCoins));
            dollOrder.setDeliverCoins(doll.getRedeemCoins());
            Charge charge = new Charge();
            Member member = baseService.selectMemberById(memberId);
            charge.setMemberId(memberId);
            charge.setCoins(member.getAccount().getCoins());
            //charge.setCoinsSum(doll.getRedeemCoins() + member.getCoins());
            charge.setCoinsSum(doll.getRedeemCoins());//练习房兑换奖励
            charge.setChargeDate(TimeUtil.getTime());
            charge.setType("income");
            charge.setDollId(doll.getId());
            charge.setChargeMethod("由<练习房抓中娃娃赠送币," + doll.getDollID() + ">兑换获取");
            if ("3".equals(machineType)) {
                charge.setChargeMethod("由<占卜房抓中娃娃赠送币," + doll.getDollID() + ">兑换获取");
            }
            charge.setChargeDate(TimeUtil.getTime());
            chargeService.updateMemberCount(charge);
            chargeService.insertChargeHistory(charge);
        }
        dollOrder.setStockValidDate(TimeUtil.plusDay(validDate));
        dollOrderDao.insertOrder(dollOrder);

        dollOrderItem.setDollOrder(dollOrder);
        dollOrderItem.setDoll(doll);
        dollOrderItem.setQuantity(dollNum);
        dollOrderItem.setCreatedDate(TimeUtil.getTime());
        dollOrderItemDao.insert(dollOrderItem);
        // 娃娃机的娃娃数量减1
        //if (doll.getQuantity() > 0) {
        //	doll.setQuantity(doll.getQuantity() - 1);
        //} else {
        //	doll.setQuantity(0);
        //}
        return dollDao.updateByPrimaryKeySelective(doll);
    }

    @Override
    public int updateByPrimaryKeySelective(DollOrder record) {
        return dollOrderDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public DollOrder selectByOrderIds(Integer[] orderIds) {
        return dollOrderDao.selectByOrderIds(orderIds);
    }

    @Override
    public List<DollOrder> selectByOrderNotIn(DollOrder record) {
        return dollOrderDao.selectByOrderNotIn(record);
    }

    @Override
    public List<DollOrderItem> selectByOrderItem(DollOrder record) {
        return dollOrderItemDao.selectByOrderItem(record);
    }

    /**
     * 申请发货
     *
     * @param memberId
     * @param orderIds
     * @param addrId
     * @return
     */
    @Override
    public ResultMap sendOrder(Integer memberId, Integer[] orderIds, Integer addrId, String note) {
        //根据抓取订单id查询可发货的寄存娃娃
        List<DollOrder> sendList = dollOrderDao.selectListByOrderIds(orderIds);
        if (sendList == null || sendList.size() == 0) {
            //logger.info("申请发货失败:" + Enviroment.SELECT_SENDORDER_FAILED);
            return new ResultMap(Enviroment.FAILE_CODE, Enviroment.SELECT_SENDORDER_FAILED);
        }
        //订单对应娃娃明细
        List<DollOrderItem> items = new ArrayList<>();
        for (DollOrder order : sendList) {
            List<DollOrderItem> item = dollOrderItemDao.getOrderItemByOrderId(order.getId());
            items.addAll(item);
        }
        if (items == null || items.size() == 0) {
            //logger.info("申请发货失败:" + Enviroment.SELECT_SENDORDER_ITEMS_FAILED);
            return new ResultMap(Enviroment.FAILE_CODE, Enviroment.SELECT_SENDORDER_ITEMS_FAILED);
        }
        ArrayList<DollOrderItem> realItems = new ArrayList<>();
        Iterator it = items.iterator();
        String dollitemids = "";
        while (it.hasNext()) {
            DollOrderItem s = (DollOrderItem) it.next();
            // 拿这个元素到新集合去找，看有没有
            if (!realItems.contains(s)) {
                realItems.add(s);
                dollitemids += s.getId() + ",";
            }
        }
        Map<String, Integer> map = new HashMap<>();
        //合并 娃娃
        for (DollOrderItem item : realItems) {
            if (map.get(item.getDollCode()) == null || map.get(item.getDollCode()) == 0) {
                map.put(item.getDollCode(), item.getQuantity());
            } else {
                Integer num = map.get(item.getDollCode());
                map.put(item.getDollCode(), num + item.getQuantity());//娃娃数量增加
            }
        }
        String dollsInfo = "";
        for (Entry<String, Integer> entry : map.entrySet()) {
            dollsInfo += entry.getKey() + "*" + entry.getValue() + ";";
        }
        // 获取最大时间的订单id
        DollOrder dollOrderids = dollOrderDao.selectByOrderIds(orderIds);
        //发货订单申请
        DollOrderGoods dollOrderGoods = new DollOrderGoods();
        String orderNum = StringUtils.getNumber("goods_");
        while (dollOrderGoodsDao.selectByOrderNum(orderNum) != null) {
            orderNum = StringUtils.getOrderNumber();
        }
        dollOrderGoods.setOrderNumber(orderNum);//申请发货订单生成
        dollOrderGoods.setOrderDate(TimeUtil.getTime());
        dollOrderGoods.setMemberId(memberId);
        dollOrderGoods.setStatus("申请发货");
        dollOrderGoods.setStockValidDate(dollOrderids.getStockValidDate());
        dollOrderGoods.setDollitemids(dollitemids);
        dollOrderGoods.setDollsInfo(dollsInfo);
        MemberAddr memberAddr = baseService.selectMemberAddrByPrimaryKey(addrId);//收货地址
        if (memberAddr == null || !memberId.equals(memberAddr.getMemberId())) {
            memberAddr = baseService.selectDefaultAddr(memberId);
        }
        dollOrderGoods.setReceiverName(memberAddr.getReceiverName());
        dollOrderGoods.setReceiverPhone(memberAddr.getReceiverPhone());
        dollOrderGoods.setProvince(memberAddr.getProvince());
        dollOrderGoods.setCity(memberAddr.getCity());
        dollOrderGoods.setCounty(memberAddr.getCounty());
        dollOrderGoods.setStreet(memberAddr.getStreet());
        dollOrderGoods.setCreatedDate(TimeUtil.getTime());
        dollOrderGoods.setNote(note);
        /*SystemPref systemPref = systemPrefDao.selectByPrimaryKey("DELIVERY_FREE_QT");

        Integer freeQt = Integer.valueOf(systemPref.getValue());
        if (orderIds.length >= freeQt) {
            dollOrderGoods.setDeliverCoins(0);
        } else {
            SystemPref deliverCoins = systemPrefDao.selectByPrimaryKey("DELIVERY_COINS");
            Integer deliverCoin = deliverCoins == null ? 0 : Integer.valueOf(deliverCoins.getValue());
            dollOrderGoods.setDeliverCoins(deliverCoin);

            Charge charge =new Charge();
			Member member = memberDao.selectById(memberId);
			charge.setMemberId(memberId);
			charge.setCoins(member.getCoins());
			//charge.setCoinsSum(doll.getRedeemCoins() + member.getCoins());
			charge.setCoinsSum(-deliverCoin);//练习房兑换奖励
			charge.setChargeDate(TimeUtil.getTime());
			charge.setType("expense");
			charge.setChargeMethod("邮费扣除"+deliverCoin);
			charge.setChargeDate(TimeUtil.getTime());
			chargeDao.updateMemberCount(charge);
			chargeDao.insertChargeHistory(charge);
        }*/
        Integer result = dollOrderGoodsDao.insertSelective(dollOrderGoods);
        if (result != null && result == 1) {
            Integer integer = dollOrderGoodsDao.selectOrderGoodsIdByDollitemids(dollitemids);
            if (integer != null && integer > 0) {
                //logger.info("申请发货成功");
                dollOrderDao.sendDoll(addrId, orderIds);
                return new ResultMap(Enviroment.RETURN_SUCCESS_MESSAGE);
            } else {
                //logger.info("申请发货失败:" + Enviroment.CREATE_ORDER_FAILED);
                return new ResultMap(Enviroment.FAILE_CODE, Enviroment.CREATE_ORDER_FAILED);
            }
        } else {
            //logger.info("申请发货失败:" + Enviroment.CREATE_ORDER_FAILED);
            return new ResultMap(Enviroment.FAILE_CODE, Enviroment.CREATE_ORDER_FAILED);
        }
    }

    /**
     * 发货 处理  已放弃使用
     */
    @Transactional
    public int updateOrderId(DollOrder record, List<DollOrder> dollOrderNotIns, List<DollOrderItem> item,
                             Integer addrId, Integer[] orderIds) {
        if (item == null || item.size() == 0) {
            return 0;
        }
        DollOrderItem dItem = new DollOrderItem();
        for (DollOrderItem dollOrder : item) {
            dItem.setDollOrder(record);
            dItem.setId(dollOrder.getId());
            dollOrderItemDao.updateOrderId(dItem);
        }
        Map<Integer, DollOrderItem> map = new HashMap<Integer, DollOrderItem>();
        // 合并详情
        for (DollOrderItem dollOrderItem : item) {
            // 如果map里不存在 就插入
            if (map.containsKey(dollOrderItem.getDoll().getId()) == false) {
                map.put(dollOrderItem.getDoll().getId(), dollOrderItem);
            } else {
                // 如果map里存在 就把该条数据删除
                DollOrderItem existItem = map.get(dollOrderItem.getDoll().getId());
                existItem.setQuantity(dollOrderItem.getQuantity() + existItem.getQuantity());
                existItem.setCreatedDate(TimeUtil.getTime());
                dollOrderItemDao.updateByPrimaryKeySelective(existItem);
                dollOrderItemDao.deleteByPrimaryKey(dollOrderItem.getId());
            }
        }

        for (DollOrder dollOrderNotIn : dollOrderNotIns) {
            dollOrderDao.deleteByPrimaryKey(dollOrderNotIn.getId());
        }
        DollOrder dOrder = new DollOrder();
        MemberAddr memberAddr = baseService.selectMemberAddrByPrimaryKey(addrId);//收货地址
        if (memberAddr == null) {
            memberAddr = baseService.selectDefaultAddr(record.getOrderBy());
        }
        SystemPref systemPref = systemPrefService.selectByPrimaryKey("DELIVERY_FREE_QT");
        Integer freeQt = Integer.valueOf(systemPref.getValue());
        if (orderIds.length >= freeQt) {
            dOrder.setDeliverCoins(0);
        }
        memberAddr.setId(addrId);
        dOrder.setDeliverDate(TimeUtil.getTime());
        dOrder.setId(record.getId());
        dOrder.setStatus("申请发货");
        dOrder.setModifiedDate(TimeUtil.getTime());
//		dOrder.setModifiedBy(dOrder.getOrderBy());
        dOrder.setMemberAddress(memberAddr);
        //logger.info("dOrder:{}", dOrder);
        return dollOrderDao.updateByPrimaryKeySelective(dOrder);
    }

    @Override
    public List<DollOrder> selectExpireOrder() {
        return dollOrderDao.selectExpireOrder();
    }

    @Override
    public List<DollOrder> selectOutTimeDolls() {
        return dollOrderDao.selectOutTimeDolls();
    }


}
