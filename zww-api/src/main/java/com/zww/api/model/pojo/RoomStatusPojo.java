package com.zww.api.model.pojo;

import java.io.Serializable;

public class RoomStatusPojo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	int roomId;
	String status;
	long activeMembers;
	MemberInfoPojo hostInfo;
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getActiveMembers() {
		return activeMembers;
	}
	public void setActiveMembers(long activeMembers) {
		this.activeMembers = activeMembers;
	}
	public MemberInfoPojo getHostInfo() {
		return hostInfo;
	}
	public void setHostInfo(MemberInfoPojo hostInfo) {
		this.hostInfo = hostInfo;
	}
	@Override
	public String toString() {
		return "RoomStatusPojo [roomId=" + roomId + ", status=" + status + ", activeMembers=" + activeMembers
//				+", roomHostId="+hostInfo.getId()+", roomHostName="+hostInfo.getName()
//				+", roomHostGender="+hostInfo.getGender()+", roomHostIcon="+hostInfo.getIconRealPath()
				+ "]";
	}

}
