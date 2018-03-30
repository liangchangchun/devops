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
 * @since 2018-01-02
 */
@TableName("t_member")
public class TMember extends Model<TMember> {

    private static final long serialVersionUID = 1L;

    /**
     * 注册用户（玩家）
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String memberID;
	private String name;
	private String mobile;
	private String password;
	@TableField("weixin_id")
	private String weixinId;
	private String gender;
	private Date birthday;
	private Integer coins;
	private Integer points;
	@TableField("catch_number")
	private Integer catchNumber;
	@TableField("register_date")
	private Date registerDate;
	@TableField("modified_date")
	private Date modifiedDate;
	@TableField("modified_by")
	private Integer modifiedBy;
	@TableField("last_login_date")
	private Date lastLoginDate;
	@TableField("last_logoff_date")
	private Date lastLogoffDate;
    /**
     * 0：登录成功 1：登录失败 
     */
	@TableField("online_flg")
	private Boolean onlineFlg;
	@TableField("icon_context_path")
	private String iconContextPath;
	@TableField("icon_file_name")
	private String iconFileName;
	@TableField("icon_real_path")
	private String iconRealPath;
    /**
     * 环信uuid
     */
	@TableField("easemob_uuid")
	private String easemobUuid;
    /**
     * 账号是否激活
     */
	@TableField("active_flg")
	private Boolean activeFlg;
    /**
     * 是否已输入邀请码
     */
	@TableField("invite_flg")
	private Boolean inviteFlg;
	@TableField("invite_flg_web")
	private Boolean inviteFlgWeb;
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
     * 0尚未充值，1已充值
     */
	@TableField("first_login")
	private Integer firstLogin;
    /**
     * 0尚未充值过，1已完成过充值
     */
	@TableField("first_charge")
	private Integer firstCharge;
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
     * 手机机型
     */
	@TableField("phone_model")
	private String phoneModel;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getCatchNumber() {
		return catchNumber;
	}

	public void setCatchNumber(Integer catchNumber) {
		this.catchNumber = catchNumber;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastLogoffDate() {
		return lastLogoffDate;
	}

	public void setLastLogoffDate(Date lastLogoffDate) {
		this.lastLogoffDate = lastLogoffDate;
	}

	public Boolean getOnlineFlg() {
		return onlineFlg;
	}

	public void setOnlineFlg(Boolean onlineFlg) {
		this.onlineFlg = onlineFlg;
	}

	public String getIconContextPath() {
		return iconContextPath;
	}

	public void setIconContextPath(String iconContextPath) {
		this.iconContextPath = iconContextPath;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public String getIconRealPath() {
		return iconRealPath;
	}

	public void setIconRealPath(String iconRealPath) {
		this.iconRealPath = iconRealPath;
	}

	public String getEasemobUuid() {
		return easemobUuid;
	}

	public void setEasemobUuid(String easemobUuid) {
		this.easemobUuid = easemobUuid;
	}

	public Boolean getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(Boolean activeFlg) {
		this.activeFlg = activeFlg;
	}

	public Boolean getInviteFlg() {
		return inviteFlg;
	}

	public void setInviteFlg(Boolean inviteFlg) {
		this.inviteFlg = inviteFlg;
	}

	public Boolean getInviteFlgWeb() {
		return inviteFlgWeb;
	}

	public void setInviteFlgWeb(Boolean inviteFlgWeb) {
		this.inviteFlgWeb = inviteFlgWeb;
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

	public Integer getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Integer firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Integer getFirstCharge() {
		return firstCharge;
	}

	public void setFirstCharge(Integer firstCharge) {
		this.firstCharge = firstCharge;
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

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TMember{" +
			"id=" + id +
			", memberID=" + memberID +
			", name=" + name +
			", mobile=" + mobile +
			", password=" + password +
			", weixinId=" + weixinId +
			", gender=" + gender +
			", birthday=" + birthday +
			", coins=" + coins +
			", points=" + points +
			", catchNumber=" + catchNumber +
			", registerDate=" + registerDate +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			", lastLoginDate=" + lastLoginDate +
			", lastLogoffDate=" + lastLogoffDate +
			", onlineFlg=" + onlineFlg +
			", iconContextPath=" + iconContextPath +
			", iconFileName=" + iconFileName +
			", iconRealPath=" + iconRealPath +
			", easemobUuid=" + easemobUuid +
			", activeFlg=" + activeFlg +
			", inviteFlg=" + inviteFlg +
			", inviteFlgWeb=" + inviteFlgWeb +
			", registerFrom=" + registerFrom +
			", lastLoginFrom=" + lastLoginFrom +
			", firstLogin=" + firstLogin +
			", firstCharge=" + firstCharge +
			", registerChannel=" + registerChannel +
			", loginChannel=" + loginChannel +
			", phoneModel=" + phoneModel +
			"}";
	}
}
