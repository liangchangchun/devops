package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberChannelDeduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.TDoll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-02
 */
public interface MemberChannelDeductMapper extends BaseMapper<MemberChannelDeduct> {

    //去重
    Integer selectByUserid(@Param("userId") Integer id);

    //批量删除
    Integer deleteAllById(@Param("ids") String[] ids);

    List<Map<String, Object>> selectLists(@Param("page") Page<MemberChannelDeduct> page, @Param("userId") String userId,@Param("name") String name, @Param("registeDate") String registeDate, @Param("endTime") String endTime,@Param("lastLoginFrom") String lastLoginFrom,@Param("channelNum") String channelNum);

}