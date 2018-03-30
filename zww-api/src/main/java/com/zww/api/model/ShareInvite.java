package com.zww.api.model;

import java.io.Serializable;
import java.util.Date;

public class ShareInvite implements Serializable {

	
	private static final long serialVersionUID = 5319339675762595464L;
	private Integer id;
	/**
	 * 邀请码编号
	 */
	private String inviteCode;//邀请码
	private String inviteMemberId;//邀请人id
	private String invitedId;//被邀请id
	private Date createDate;//邀请码填写时间
	private Integer state;//充值奖励状态0未奖励,1,奖励过
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public String getInviteMemberId() {
		return inviteMemberId;
	}
	public void setInviteMemberId(String inviteMemberId) {
		this.inviteMemberId = inviteMemberId;
	}
	public String getInvitedId() {
		return invitedId;
	}
	public void setInvitedId(String invitedId) {
		this.invitedId = invitedId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ShareInvite [id=" + id +
				", inviteCode=" + inviteCode +
				", inviteMemberId=" + inviteMemberId +
				", invitedId=" + invitedId +
				", createDate=" + createDate +
				", state=" + state + "]";
	}

}
