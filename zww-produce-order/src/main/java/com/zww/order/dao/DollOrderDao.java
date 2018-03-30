package com.zww.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.DollOrder;


/**
 * Author: mwan Version: 1.1 Date: 2017/09/26 Description: 娃娃抓取成功后的订单DAO层.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
public interface DollOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DollOrder record);

    int insertSelective(DollOrder record);

    DollOrder selectByPrimaryKey(Integer id);

    //发货申请
    int sendDoll(@Param("addressId") Integer addressId, @Param("orderIds") Integer[] orderIds);

    int updateByPrimaryKeySelective(DollOrder record);

    int updateByPrimaryKey(DollOrder record);

    int insertOrder(DollOrder record);

    DollOrder selectByOrderNum(String orderNum);

    DollOrder selectByOrderIds(Integer[] orderIds);

    List<DollOrder> selectListByOrderIds(Integer[] orderIds);

    List<DollOrder> selectByOrderNotIn(DollOrder record);

    List<DollOrder> selectExpireOrder();

    List<DollOrder> getOrdersByStatus(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("phone") String phone);

    int totalCount(@Param("phone") String phone);

    List<DollOrder> getOutOrdersByStatus(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("phone") String phone, @Param("outGoodsId") int outGoodsId);

    int totalCountOutOrders(@Param("phone") String phone, @Param("outGoodsId") int outGoodsId);

    List<DollOrder> selectListByPrimaryKey(Integer[] orderIds);

    List<DollOrder> selectOutTimeDolls();

}
