package com.stylefeng.guns.modular.backend.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-02
 */
public interface IChargeOrderService extends IService<ChargeOrder> {

    List<Map<String, Object>> selectList(Page<ChargeOrder> page, String memberName, Integer memberId, Integer chargeruleid, Integer chargeState, String registeDate, String endtime);

    //渠道
    List<Map<String, Object>> selectListChannel(Page<ChargeOrder> page,String channelNum, String lastLoginFrom,String memberName, Integer memberId, Integer chargeruleid, Integer chargeState, String registeDate, String endtime);

    //小妖精渠道
    List<Map<String, Object>> xiaoyaojingSelectListChannel(Page<ChargeOrder> page,String channelNum, String lastLoginFrom,String memberName, Integer memberId, Integer chargeruleid, Integer chargeState, String registeDate, String endtime);


    //总金额
    Double selectAllMoney(String memberName, Integer memberId, String chargeName, String chargeState, String registeDate, String endtime);
    //总人数
    Integer selectAllPerson(String memberName, Integer memberId, String chargeName, String chargeState, String registeDate, String endtime);


    //渠道总金额
    Double selectAllMoneyChannel(String channelNum,String lastLoginFrom,String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime);
    //渠道总人数
    Integer selectAllPersonChannel(String channelNum,String lastLoginFrom,String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime);

    //渠道总金额
    Double xiaoyaojingSelectAllMoneyChannel(String channelNum,String lastLoginFrom,String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime);
    //渠道总人数
    Integer xiaoyaojingSelectAllPersonChannel(String channelNum,String lastLoginFrom,String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime);

}
