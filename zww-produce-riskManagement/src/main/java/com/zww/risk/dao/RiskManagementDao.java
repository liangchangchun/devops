package com.zww.risk.dao;

import com.zww.api.model.RiskManagement;

/**
 * Created by SUN on 2018/3/2.
 */
public interface RiskManagementDao {

    RiskManagement selectByMemberId(int memberId);

    int updateById(RiskManagement riskManagement);

    void init(int memberId);

    int selectIMEICount(String imei);
}