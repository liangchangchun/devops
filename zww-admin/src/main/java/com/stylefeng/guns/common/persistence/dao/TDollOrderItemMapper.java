package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TDollOrderItem;
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
 * @since 2018-01-25
 */
public interface TDollOrderItemMapper extends BaseMapper<TDollOrderItem> {

    List<Map<String, Object>> selectLists(@Param("page") Page<TDollOrderItem> page, @Param("orderId") Integer orderId);

    //按房间订单id查询
    TDollOrderItem selectByOrderId(Integer orderId);
}