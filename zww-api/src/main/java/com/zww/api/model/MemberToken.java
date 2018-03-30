package com.zww.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: lgq
 * Version: 1.1
 * Date: 2017/09/20
 * Description: token持久化类.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
public class MemberToken implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer memberId;
	private String token;
	
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "MemberToken [memberId=" + memberId + ", token=" + token + "]";
	}
	
}
