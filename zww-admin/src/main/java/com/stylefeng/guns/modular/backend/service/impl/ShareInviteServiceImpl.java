package com.stylefeng.guns.modular.backend.service.impl;

import com.stylefeng.guns.common.persistence.model.ShareInvite;
import com.stylefeng.guns.common.persistence.dao.ShareInviteMapper;
import com.stylefeng.guns.modular.backend.service.IShareInviteService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-26
 */
@Service
public class ShareInviteServiceImpl extends ServiceImpl<ShareInviteMapper, ShareInvite> implements IShareInviteService {

	@Autowired
	ShareInviteMapper shareInviteMapper;
	
	@Override
	public List<Map<String, Object>> selectMember(Page<ShareInvite> page,Integer inviteType,  String startDate, String endDate, String memberid,
			String name) {
		if (inviteType==null || inviteType==0) {//查看邀请人列表
			return shareInviteMapper.selectMember(page, startDate, endDate, memberid, name);
		}else {//查看被邀请人列表
			return shareInviteMapper.selectInvitedMember(page, startDate, endDate, memberid, name);
		}
	}
	
}
