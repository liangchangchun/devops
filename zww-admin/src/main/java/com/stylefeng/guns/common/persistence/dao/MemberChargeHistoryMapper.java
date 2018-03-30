package com.stylefeng.guns.common.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberChargeHistory;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-03
 */
public interface MemberChargeHistoryMapper extends BaseMapper<MemberChargeHistory> {

    //分页
    List<Map<String, Object>> selectList(@Param("page") Page<MemberChargeHistory> page,@Param("condition") Integer condition);

    //更新用户金币
    Integer updateMemberCount(MemberChargeHistory chargeRecord);

    //添加消费记录记录
    Integer insertChargeHistory(MemberChargeHistory chargeRecord);
}