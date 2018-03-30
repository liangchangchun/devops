package com.zww.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.*;


/**
 * Author: mwan
 * Version: 1.1
 * Date: 2017/09/26
 * Description: 娃娃抓取成功后的订单明细DAO层.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
public interface DollOrderItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DollOrderItem record);

    int insertSelective(DollOrderItem record);

    DollOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DollOrderItem record);

    int updateByPrimaryKey(DollOrderItem record);

    List<DollOrderItem> selectByMemberId(Integer memberId);

    List<DollOrderItem> selectByMemberIdOrderStatus(@Param("memberId") Integer memberId, @Param("orderStatus") String orderStatus);

    List<DollOrderItem> selectByOrderItem(DollOrder record);

    int updateOrderId(DollOrderItem item);

    List<DollOrderItem> getOrderItemByOrderId(Integer id);
}