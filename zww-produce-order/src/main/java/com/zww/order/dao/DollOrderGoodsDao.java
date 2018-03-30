package com.zww.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.DollOrderGoods;


public interface DollOrderGoodsDao {

	int updateByPrimaryKeySelective(DollOrderGoods record);

	int updateOrderGoodsStatus(DollOrderGoods record);

	Integer insertSelective(DollOrderGoods record);
	
	int insertOrder(DollOrderGoods record);
	/**
	 * 按编号查询 发货订单
	 * @param orderNum
	 * @return
	 */
	DollOrderGoods selectByOrderNum(String orderNum);
	
	DollOrderGoods selectByPrimaryKey(Integer id);
	/**
	 * 分页查询发货订单状态
	 * @param begin
	 * @param pageSize
	 * @param status
	 * @param phone
	 * @return
	 */
	List<DollOrderGoods> getOrdersByStatus(@Param("begin")int begin,@Param("pageSize")int pageSize,@Param("status")String status,@Param("phone")String phone);
	
	int totalCount(@Param("status")String status,@Param("phone")String phone);


	//获取待发货信息
	List<DollOrderGoods> getAllOrdersByStatus(@Param("begin")int begin,@Param("pageSize")int pageSize,@Param("phone")String phone);

	int totalCountAll(@Param("phone")String phone);

    Integer selectOrderGoodsIdByDollitemids(String dollitemids);
}
