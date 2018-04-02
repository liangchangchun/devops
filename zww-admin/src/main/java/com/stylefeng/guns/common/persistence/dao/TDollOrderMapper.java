package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TDollOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-30
 */
public interface TDollOrderMapper extends BaseMapper<TDollOrder> {

    //根据订单编号查询
    TDollOrder selectByOrderNum(String orderNum);

    //查询寄存中订单
    List<Map<String, Object>> selectTDollOrderMember(@Param("page") Page<TDollOrder> page, @Param("memberId") String memberId, @Param("phone") String phone);

    //查询待发货订单
    List<Map<String, Object>> selectTDollOrder(@Param("page") Page<TDollOrder> page, @Param("memberId") String memberId, @Param("phone") String phone);

    //查询已发货订单
    List<Map<String, Object>> selectTDollOrderOut(@Param("page") Page<TDollOrder> page, @Param("memberId") String memberId, @Param("phone") String phone);


}