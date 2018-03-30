package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-02
 */
public interface ChargeOrderMapper extends BaseMapper<ChargeOrder> {

    List<Map<String, Object>> selectList(@Param("page") Page<ChargeOrder> page, @Param("memberName")String memberName, @Param("memberId")Integer memberId, @Param("chargeruleid")Integer chargeruleid, @Param("chargeState")Integer chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);

    //某个用户充值
    List<Map<String, Object>> selectListOneMember(@Param("page") Page<ChargeOrder> page, @Param("memberName")String memberName, @Param("memberId")Integer memberId, @Param("chargeruleid")Integer chargeruleid, @Param("chargeState")Integer chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);

    //某个用户总金额
    Double selectAllMoneyOneMember(@Param("memberName")String memberName, @Param("memberId")Integer memberId, @Param("chargeName")String chargeName, @Param("chargeState")String chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);


    //渠道
    List<Map<String, Object>> selectListChannel(@Param("page") Page<ChargeOrder> page,@Param("channelNum")String channelNum,@Param("lastLoginFrom") String lastLoginFrom, @Param("memberName")String memberName, @Param("memberId")Integer memberId, @Param("chargeruleid")Integer chargeruleid, @Param("chargeState")Integer chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);

    //小妖精渠道
    List<Map<String, Object>> xiaoyaojingSelectListChannel(@Param("page") Page<ChargeOrder> page,@Param("channelNum")String channelNum,@Param("lastLoginFrom") String lastLoginFrom, @Param("memberName")String memberName, @Param("memberId")Integer memberId, @Param("chargeruleid")Integer chargeruleid, @Param("chargeState")Integer chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);


    //总金额
    Double selectAllMoney(@Param("memberName")String memberName, @Param("memberId")Integer memberId, @Param("chargeName")String chargeName, @Param("chargeState")String chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);

    //总人数
    Integer selectAllPerson(@Param("memberName")String memberName, @Param("memberId")Integer memberId, @Param("chargeName")String chargeName, @Param("chargeState")String chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);

    //渠道总金额
    Double selectAllMoneyChannel(@Param("channelNum")String channelNum,@Param("lastLoginFrom")String lastLoginFrom,@Param("memberName")String memberName, @Param("memberId")String memberId, @Param("chargeName")String chargeName, @Param("chargeState")String chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);
    //渠道总人数
    Integer selectAllPersonChannel(@Param("channelNum")String channelNum,@Param("lastLoginFrom")String lastLoginFrom,@Param("memberName")String memberName, @Param("memberId")String memberId, @Param("chargeName")String chargeName, @Param("chargeState")String chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);

    //小妖精渠道总金额
    Double xiaoyaojingSelectAllMoney(@Param("channelNum")String channelNum,@Param("lastLoginFrom")String lastLoginFrom,@Param("memberName")String memberName, @Param("memberId")String memberId, @Param("chargeName")String chargeName, @Param("chargeState")String chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);
    //小妖精渠道总人数
    Integer xiaoyaojingSelectAllPerson(@Param("channelNum")String channelNum,@Param("lastLoginFrom")String lastLoginFrom,@Param("memberName")String memberName, @Param("memberId")String memberId, @Param("chargeName")String chargeName, @Param("chargeState")String chargeState, @Param("registeDate")String registeDate, @Param("endtime")String endtime);

}