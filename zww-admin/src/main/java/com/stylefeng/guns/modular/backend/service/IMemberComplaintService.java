package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberComplaint;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.TChargeRules;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
public interface IMemberComplaintService extends IService<MemberComplaint> {

    //待处理
    List<Map<String, Object>> selectMemberComplain(Page<MemberComplaint> page, String memberID, String complaintChannel,String catchStates,Double vipGroup);

    //已处理
    List<Map<String, Object>> selectMemberComplainDone(Page<MemberComplaint> page, String memberID, String complaintChannel,String catchStates,Integer checkState);

    //申诉通过
    boolean complaintBack(MemberComplaint memberComplaint, Integer backStatus);
}
