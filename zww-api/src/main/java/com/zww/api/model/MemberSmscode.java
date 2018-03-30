package com.zww.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author: lgq
 * Version: 1.1
 * Date: 2017/09/20
 * Description: token持久化类.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
public class MemberSmscode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mobile;
	private String smscode;
	private Timestamp validStartTime;
	private Timestamp validEndTime;
	
	
	public Timestamp getValidStartTime() {
		return validStartTime;
	}
	public void setValidStartTime(Timestamp validStartTime) {
		this.validStartTime = validStartTime;
	}
	public Timestamp getValidEndTime() {
		return validEndTime;
	}
	public void setValidEndTime(Timestamp validEndTime) {
		this.validEndTime = validEndTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSmscode() {
		return smscode;
	}
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
	@Override
	public String toString() {
		return "MemberSmscode [mobile=" + mobile + ", smscode=" + smscode + ", validStartTime=" + validStartTime
				+ ", validEndTime=" + validEndTime + "]";
	}
	
}
