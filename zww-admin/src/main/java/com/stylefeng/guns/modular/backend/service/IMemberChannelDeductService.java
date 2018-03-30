package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberChannelDeduct;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.TDoll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孔欢欢
 * @since 2018-03-02
 */
public interface IMemberChannelDeductService extends IService<MemberChannelDeduct> {

    //批量删除图片
    Boolean deleteAllById(String[] id);

    List<Map<String, Object>> selectLists(Page<MemberChannelDeduct> page, String useId, String name,String registeDate, String endTime, String lastLoginFrom,String channelNum);

}
