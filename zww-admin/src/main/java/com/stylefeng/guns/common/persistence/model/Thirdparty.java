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
 * @since 2018-03-26
 */
@TableName("thirdparty")
public class Thirdparty extends Model<Thirdparty> {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 第三方appkey
     */
	private String appkey;
    /**
     * 秘钥
     */
	@TableField("password_key")
	private String passwordKey;
    /**
     * 备注
     */
	private String content;
    /**
     * 类型
     */
	private Integer type;
    /**
     * 创建时间
     */
	@TableField("created_date")
	private Date createdDate;
    /**
     * 修改时间
     */
	@TableField("modified_date")
	private Date modifiedDate;
    /**
     * 描述
     */
	private String description;
    /**
     * 是否启用
     */
	@TableField("start_using")
	private Integer startUsing;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getPasswordKey() {
		return passwordKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStartUsing() {
		return startUsing;
	}

	public void setStartUsing(Integer startUsing) {
		this.startUsing = startUsing;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Thirdparty{" +
			"id=" + id +
			", appkey=" + appkey +
			", passwordKey=" + passwordKey +
			", content=" + content +
			", type=" + type +
			", createdDate=" + createdDate +
			", modifiedDate=" + modifiedDate +
			", description=" + description +
			", startUsing=" + startUsing +
			"}";
	}
}
