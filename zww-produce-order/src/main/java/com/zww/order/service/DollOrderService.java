package com.zww.order.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.zww.api.model.DollOrder;
import com.zww.api.model.DollOrderItem;
import com.zww.common.ResultMap;


/**
 * Author: mwan
 * Version: 1.1
 * Date: 2017/09/27
 * Description: 娃娃发货或寄存订单业务接口.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
public interface DollOrderService {

    List<DollOrder> selectListByOrderIds(Integer[] orderIds);

    /**
     * 获取订单
     */
    DollOrder selectByPrimaryKey(Integer id);

    List<DollOrder> selectListByPrimaryKey(Integer[] orderIds);

    /**
     * 根据用户id获取订单详情
     */
    List<DollOrderItem> selectItemsByMemberId(Integer memberId);

    List<DollOrderItem> selectItemsByMemberIdOrderStatus(Integer memberId, String orderStatus);

    Integer insertOrder(Integer memberId, Integer dollId, Integer dollNum);

    int updateByPrimaryKeySelective(DollOrder record);

    DollOrder selectByOrderIds(Integer[] orderIds);

    List<DollOrder> selectByOrderNotIn(DollOrder record);

    List<DollOrderItem> selectByOrderItem(DollOrder record);

    int updateOrderId(DollOrder record, List<DollOrder> dollOrderNotIn, List<DollOrderItem> item, Integer addrId, Integer[] orderIds);

    /**
     * 申请发货
     *
     * @param memberId
     * @param orderIds
     * @param addrId
     * @return
     */
    ResultMap sendOrder(Integer memberId, Integer[] orderIds, Integer addrId, String note);

    List<DollOrder> selectExpireOrder();

    List<DollOrder> selectOutTimeDolls();

}



