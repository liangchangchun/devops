package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

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
 * @since 2018-03-15
 */
@TableName("t_app_version")
public class TAppVersion extends Model<TAppVersion> {

    private static final long serialVersionUID = 1L;

    @TableId("app_key")
	private String appKey;
	private String version;
	@TableField("upgrade_url")
	private String upgradeUrl;
	private String content;
	@TableField("hide_flg")
	private String hideFlg;

	@TableField("force_update")
	private Integer forceUpdate;

	@TableField("open_update")
	private Integer openUpdate;


	public Integer getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Integer forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public void setOpenUpdate(Integer openUpdate) {
		this.openUpdate = openUpdate;
	}

	public Integer getOpenUpdate() {
		return openUpdate;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUpgradeUrl() {
		return upgradeUrl;
	}

	public void setUpgradeUrl(String upgradeUrl) {
		this.upgradeUrl = upgradeUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHideFlg() {
		return hideFlg;
	}

	public void setHideFlg(String hideFlg) {
		this.hideFlg = hideFlg;
	}

	@Override
	protected Serializable pkVal() {
		return this.appKey;
	}

	@Override
	public String toString() {
		return "TAppVersion{" +
			"appKey=" + appKey +
			", version=" + version +
			", upgradeUrl=" + upgradeUrl +
			", content=" + content +
			", hideFlg=" + hideFlg +
			", forceUpdate=" + forceUpdate +
			", openUpdate=" + openUpdate +
			"}";
	}
}
