package com.zww.account.dao;

import com.zww.api.model.MemberToken;

/**
 * Author: lgq Version: 1.1 Date: 2017/09/20 Description: 验证token. Copyright
 * (c) 2017 伴飞网络. All rights reserved.
 */
public interface ValidateTokenDao {

	MemberToken selectByToken(String token);
	
	int deleteByMemberId(int memberId);
	
	MemberToken selectByMemberId(int memberId);
	
	int updateToken(MemberToken mtoken);

}