package com.stylefeng.guns.modular.backend.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.MemberWhiteList;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.TDoll;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-03
 */
public interface IMemberWhiteListService extends IService<MemberWhiteList> {

    MemberWhiteList selectIdByMemberId(String memberId);

    List<Map<String, Object>> selectLists(Page<MemberWhiteList> page, String memberId, String userName);

}
