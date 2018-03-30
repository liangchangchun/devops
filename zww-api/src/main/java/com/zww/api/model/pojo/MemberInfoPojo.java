package com.zww.api.model.pojo;

import java.io.Serializable;

/**
 * Author: mwan
 * Version: 1.1
 * Description: 用户常用信息pojo类.
 * Copyright (c) 2018 zww网络. All rights reserved.
 */
public class MemberInfoPojo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String gender;
	private String iconRealPath;
	private String memberID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIconRealPath() {
		return iconRealPath;
	}
	public void setIconRealPath(String iconRealPath) {
		this.iconRealPath = iconRealPath;
	}
	
	@Override
	public String toString() {
		return "MemberInfoPojo [id="+id+", name=" + name + ", gender=" + gender + ", iconRealPath=" + iconRealPath + "]";
	}
	
}
