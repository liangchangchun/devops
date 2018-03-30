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
 * @author 孔欢欢
 * @since 2018-03-02
 */
@TableName("member_channel_deduct")
public class MemberChannelDeduct extends Model<MemberChannelDeduct> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 用户编号
     */
	private String memberID;
    /**
     * 用户名
     */
	private String name;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 微信id
     */
	@TableField("weixin_id")
	private String weixinId;
    /**
     * 性别
     */
	private String gender;
    /**
     * 手机机型
     */
	@TableField("phone_model")
	private String phoneModel;
    /**
     * 注册时间
     */
	@TableField("register_date")
	private Date registerDate;
    /**
     * 最近登录时间
     */
	@TableField("last_login_date")
	private Date lastLoginDate;
    /**
     * 注册渠道号
     */
	@TableField("register_channel")
	private String registerChannel;
    /**
     * 登陆渠道号
     */
	@TableField("login_channel")
	private String loginChannel;
    /**
     * 注册设备
     */
	@TableField("register_from")
	private String registerFrom;
    /**
     * 登录设备
     */
	@TableField("last_login_from")
	private String lastLoginFrom;
    /**
     * 0：登录成功 1：登录失败
     */
	@TableField("online_flg")
	private Boolean onlineFlg;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
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

	public String getRegisterChannel() {
		return registerChannel;
	}

	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
	}

	public String getLoginChannel() {
		return loginChannel;
	}

	public void setLoginChannel(String loginChannel) {
		this.loginChannel = loginChannel;
	}

	public String getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(String registerFrom) {
		this.registerFrom = registerFrom;
	}

	public String getLastLoginFrom() {
		return lastLoginFrom;
	}

	public void setLastLoginFrom(String lastLoginFrom) {
		this.lastLoginFrom = lastLoginFrom;
	}

	public Boolean getOnlineFlg() {
		return onlineFlg;
	}

	public void setOnlineFlg(Boolean onlineFlg) {
		this.onlineFlg = onlineFlg;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberChannelDeduct{" +
			"id=" + id +
			", userId=" + userId +
			", memberID=" + memberID +
			", name=" + name +
			", mobile=" + mobile +
			", weixinId=" + weixinId +
			", gender=" + gender +
			", phoneModel=" + phoneModel +
			", registerDate=" + registerDate +
			", lastLoginDate=" + lastLoginDate +
			", registerChannel=" + registerChannel +
			", loginChannel=" + loginChannel +
			", registerFrom=" + registerFrom +
			", lastLoginFrom=" + lastLoginFrom +
			", onlineFlg=" + onlineFlg +
			"}";
	}
}
