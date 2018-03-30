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
 * @author konghuanhuan
 * @since 2018-01-25
 */
@TableName("t_member_addr")
public class TMemberAddr extends Model<TMemberAddr> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("member_id")
	private Integer memberId;
	@TableField("receiver_name")
	private String receiverName;
	@TableField("receiver_phone")
	private String receiverPhone;
	private String province;
	private String city;
	private String county;
	private String street;
	@TableField("created_date")
	private Date createdDate;
	@TableField("modified_date")
	private Date modifiedDate;
	@TableField("default_flg")
	private Integer defaultFlg;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public Integer getDefaultFlg() {
		return defaultFlg;
	}

	public void setDefaultFlg(Integer defaultFlg) {
		this.defaultFlg = defaultFlg;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TMemberAddr{" +
			"id=" + id +
			", memberId=" + memberId +
			", receiverName=" + receiverName +
			", receiverPhone=" + receiverPhone +
			", province=" + province +
			", city=" + city +
			", county=" + county +
			", street=" + street +
			", createdDate=" + createdDate +
			", modifiedDate=" + modifiedDate +
			", defaultFlg=" + defaultFlg +
			"}";
	}
}
