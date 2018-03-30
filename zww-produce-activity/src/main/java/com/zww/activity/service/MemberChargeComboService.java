package com.zww.activity.service;

import com.zww.common.ResultMap;

/**
 * Created by SUN on 2018/1/17.
 */
public interface MemberChargeComboService {

    /**
     * 周卡领取
     *
     * @param memberId
     * @return
     */
    ResultMap weeksCombo(Integer memberId);

    /**
     * 月卡领取
     *
     * @param memberId
     * @return
     */
    ResultMap monthCombo(Integer memberId);

}
