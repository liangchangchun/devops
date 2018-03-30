package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.ChargeOrder;
import com.stylefeng.guns.common.persistence.dao.ChargeOrderMapper;
import com.stylefeng.guns.modular.backend.service.IChargeOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-02
 */
@Service
public class ChargeOrderServiceImpl extends ServiceImpl<ChargeOrderMapper, ChargeOrder> implements IChargeOrderService {

    @Autowired
    private ChargeOrderMapper chargeOrderMapper;

    @Override
    public List<Map<String, Object>> selectList(Page<ChargeOrder> page,String memberName, Integer memberId, Integer chargeruleid, Integer chargeState, String registeDate, String endtime)
    {
        return chargeOrderMapper.selectList(page,memberName,memberId,chargeruleid,chargeState,registeDate,endtime);
    }

    @Override
    public List<Map<String, Object>> selectListChannel(Page<ChargeOrder> page,String channelNum,String lastLoginFrom, String memberName, Integer memberId, Integer chargeruleid, Integer chargeState, String registeDate, String endtime)
    {
        return chargeOrderMapper.selectListChannel(page,channelNum,lastLoginFrom,memberName,memberId,chargeruleid,chargeState,registeDate,endtime);
    }

    @Override
    public List<Map<String, Object>> xiaoyaojingSelectListChannel(Page<ChargeOrder> page, String channelNum, String lastLoginFrom, String memberName, Integer memberId, Integer chargeruleid, Integer chargeState, String registeDate, String endtime) {
        return chargeOrderMapper.xiaoyaojingSelectListChannel(page,channelNum,lastLoginFrom,memberName,memberId,chargeruleid,chargeState,registeDate,endtime);
    }



    @Override
    public Double selectAllMoney(String memberName, Integer memberId, String chargeName, String chargeState, String registeDate, String endtime) {
        return chargeOrderMapper.selectAllMoney(memberName,memberId,chargeName,chargeState,registeDate,endtime);
    }

    @Override
    public Integer selectAllPerson(String memberName, Integer memberId, String chargeName, String chargeState, String registeDate, String endtime) {
        return chargeOrderMapper.selectAllPerson(memberName,memberId,chargeName,chargeState,registeDate,endtime);
    }



    @Override
    public Double selectAllMoneyChannel(String channelNum, String lastLoginFrom, String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime) {
        return chargeOrderMapper.selectAllMoneyChannel(channelNum,lastLoginFrom,memberName,memberId,chargeName,chargeState,registeDate,endtime);
    }

    @Override
    public Integer selectAllPersonChannel(String channelNum, String lastLoginFrom, String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime) {
        return chargeOrderMapper.selectAllPersonChannel(channelNum,lastLoginFrom,memberName,memberId,chargeName,chargeState,registeDate,endtime);
    }


    @Override
    public Double xiaoyaojingSelectAllMoneyChannel(String channelNum, String lastLoginFrom, String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime) {
        return chargeOrderMapper.xiaoyaojingSelectAllMoney(channelNum,lastLoginFrom,memberName,memberId,chargeName,chargeState,registeDate,endtime);
    }

    @Override
    public Integer xiaoyaojingSelectAllPersonChannel(String channelNum, String lastLoginFrom, String memberName, String memberId, String chargeName, String chargeState, String registeDate, String endtime) {
        return chargeOrderMapper.xiaoyaojingSelectAllPerson(channelNum,lastLoginFrom,memberName,memberId,chargeName,chargeState,registeDate,endtime);
    }

}
