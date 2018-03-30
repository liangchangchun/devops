package com.zww.api.model.vo;

import java.io.Serializable;

public class DollTopicAll implements Serializable {
	
	private static final long serialVersionUID = -8301859233599255823L;
	private Integer id;
	private Integer dollId;		 //娃娃机序号
	private String dollName;    //娃娃机名称
	private Integer roomType;   //房间类型
	private Integer topicType;   //主题类型
	private String topicName;   //主题名称
	private Integer groupid;       //房间分组
	private String machineCode;       //机器号

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDollId() {
		return dollId;
	}
	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}
	public String getDollName() {
		return dollName;
	}
	public void setDollName(String dollName) {
		this.dollName = dollName;
	}
	public Integer getRoomType() {
		return roomType;
	}
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	public Integer getTopicType() {
		return topicType;
	}
	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	@Override
	public String toString() {
		return "DollTopic [id=" + id + ", dollId=" + dollId + ", dollName=" + dollName + ", roomType="
				+ roomType + ", topicType=" + topicType + ", topicName=" + topicName + ", groupid=" + groupid
				+ ", machineCode=" + machineCode +"]";

	}
}
