package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.ShareInvite;
import com.stylefeng.guns.common.persistence.model.TDollOrder;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-30
 */
public interface ITDollOrderService extends IService<TDollOrder> {

    //待发货
    List<Map<String, Object>> selectTDollOrder(Page<TDollOrder> page, String memberId, String phone);

    //已发货
    List<Map<String, Object>> selectTDollOrderOut(Page<TDollOrder> page, String memberId, String phone);

    //按订单编号查询
    TDollOrder selectByorderNum(String orderNum);

    //违规返币
    boolean dollBackCoins(Integer tDollOrderId, String memberId);

    //反娃娃
    boolean backDoll(TDollOrder tDollOrder);
}
