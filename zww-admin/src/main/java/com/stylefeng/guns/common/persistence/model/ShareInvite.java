package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-26
 */
@TableName("share_invite")
public class ShareInvite extends Model<ShareInvite> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 邀请人编码memberId
     */
	@TableField("invite_code")
	private String inviteCode;
    /**
     * 邀请人id
     */
	@TableField("invite_member_id")
	private String inviteMemberId;
    /**
     * 被邀请人id
     */
	@TableField("invited_id")
	private String invitedId;
    /**
     * 邀请时间
     */
	@TableField("create_date")
	private Date createDate;
    /**
     * 充值奖励状态0未奖励,1,奖励过
     */
	private Integer state;
	
	/**
	 * 用户名称   可以是邀请人用户名称 也可以是被邀请人名称
	 */
	@TableField(exist=false)
	private String name;
	@TableField(exist=false)
	private String gender;
	@TableField(exist=false)
	private String genderName;
	@TableField(exist=false)
	private String mobile;
	@TableField(exist=false)
	private Date registerDate;
	@TableField(exist=false)
	private Date lastLoginDate;
	@TableField(exist=false)
	private Boolean onlineFlg;
	/**
	 * 邀请人数
	 */
	@TableField(exist=false)
	private Integer inviteNum;
	@TableField(exist=false)
	private String invitedTime;
	
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Boolean getOnlineFlg() {
		return onlineFlg;
	}

	public void setOnlineFlg(Boolean onlineFlg) {
		this.onlineFlg = onlineFlg;
	}
	
	

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}

	public String getInvitedTime() {
		return invitedTime;
	}

	public void setInvitedTime(String invitedTime) {
		this.invitedTime = invitedTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ShareInvite{" +
			"id=" + id +
			", inviteCode=" + inviteCode +
			", inviteMemberId=" + inviteMemberId +
			", invitedId=" + invitedId +
			", createDate=" + createDate +
			", state=" + state +
			"}";
	}
}
