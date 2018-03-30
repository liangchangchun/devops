package com.stylefeng.guns.modular.backend.service;

import com.stylefeng.guns.common.persistence.model.ShareInvite;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-26
 */
public interface IShareInviteService extends IService<ShareInvite> {

	List<Map<String, Object>> selectMember(Page<ShareInvite> page, Integer inviteType, String startDate, String endDate, String memberid, String name);
}
