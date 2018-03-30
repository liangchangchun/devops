package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-03-16
 */
@TableName("doll_batch_update")
public class DollBatchUpdate extends Model<DollBatchUpdate> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 机器id
     */
	@TableField("doll_id")
	private Integer dollId;

	@TableField(exist = false)
	private String tbimgRealPath;
	@TableField(exist = false)
	private String name;
	@TableField(exist = false)
	private String machineStatus;
	@TableField(exist = false)
	private Integer machineType;
	@TableField(exist = false)
	private Integer dollAddressId;
	@TableField(exist = false)
	private String machineCode;

	@TableField(exist=false)
	private String province;//省
	@TableField(exist=false)
	private String city;//市
	@TableField(exist=false)
	private String county;//区
	@TableField(exist=false)
	private String street;//街道
	@TableField(exist=false)
	private String person;

	public String getTbimgRealPath() {
		return tbimgRealPath;
	}

	public void setTbimgRealPath(String tbimgRealPath) {
		this.tbimgRealPath = tbimgRealPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMachineStatus() {
		return machineStatus;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}

	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}

	public Integer getDollAddressId() {
		return dollAddressId;
	}

	public void setDollAddressId(Integer dollAddressId) {
		this.dollAddressId = dollAddressId;
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

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DollBatchUpdate{" +
			"id=" + id +
			", dollId=" + dollId +
			", name=" + name +
			", tbimgRealPath=" + tbimgRealPath +
			", machineStatus=" + machineStatus +
			", machineType=" + machineType +
			", dollAddressId=" + dollAddressId +
			", machineCode=" + machineCode +
			"}";
	}
}
