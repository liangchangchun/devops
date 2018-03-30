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
 * @author stylefeng
 * @since 2018-01-24
 */
@TableName("doll_repairs")
public class DollRepairs extends Model<DollRepairs> {

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
     * 机器id
     */
	@TableField("doll_id")
	private Integer dollId;
    /**
     * 报修原因
     */
	@TableField("repairs_reason")
	private String repairsReason;
    /**
     * 报修时间
     */
	@TableField("create_date")
	private Date createDate;
    /**
     * 修改时间
     */
	@TableField("modified_date")
	private Date modifiedDate;

	@TableField(exist=false)
	private String memberID;//用户编号

	@TableField(exist=false)
	private String name;//用户名

	@TableField(exist=false)
	private String dollName;//机器名

	@TableField(exist=false)
	private String tbimgRealPath;//娃娃机头像

	@TableField(exist=false)
	private String machineCode;//机器号

	@TableField(exist=false)
	private String machineStatus;//机器状态

	@TableField(exist=false)
	private String province;//机器 省

	@TableField(exist=false)
	private String city;//机器 市

	@TableField(exist=false)
	private String county;//机器 区

	@TableField(exist=false)
	private String street;//机器 街


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

	public String getDollName() {
		return dollName;
	}

	public void setDollName(String dollName) {
		this.dollName = dollName;
	}

	public String getTbimgRealPath() {
		return tbimgRealPath;
	}

	public void setTbimgRealPath(String tbimgRealPath) {
		this.tbimgRealPath = tbimgRealPath;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
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

	public Integer getDollId() {
		return dollId;
	}

	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}

	public String getRepairsReason() {
		return repairsReason;
	}

	public void setRepairsReason(String repairsReason) {
		this.repairsReason = repairsReason;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DollRepairs{" +
			"id=" + id +
			", userId=" + userId +
			", dollId=" + dollId +
			", repairsReason=" + repairsReason +
			", memberID=" + memberID +
			", name=" + name +
			", dollName=" + dollName +
			", tbimgRealPath=" + tbimgRealPath +
			", machineCode=" + machineCode +
			", machineStatus=" + machineStatus +
			", province=" + province +
			", city=" + city +
			", county=" + county +
			", street=" + street +
			", createDate=" + createDate +
			", modifiedDate=" + modifiedDate +
			"}";
	}
}
