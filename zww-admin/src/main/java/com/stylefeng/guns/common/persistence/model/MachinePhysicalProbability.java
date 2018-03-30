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
 * @since 2018-02-11
 */
@TableName("machine_physical_probability")
public class MachinePhysicalProbability extends Model<MachinePhysicalProbability> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 娃娃机id
     */
	@TableField("doll_id")
	private Integer dollId;
    /**
     * 强力电压（0-48）
     */
	private Integer strongVoltage;
    /**
     * 弱一电压（0-48）
     */
	private Integer weakOneVoltage;
    /**
     * 弱二电压（0-48）
     */
	private Integer weakTwoVoltage;
    /**
     * 强力时间（0-100 0.1s）
     */
	private Integer strongTime;
    /**
     * 弱一时间（0-100 0.1s）
     */
	private Integer weakTime;
    /**
     * 创建时间
     */
	@TableField("created_date")
	private Date createdDate;
    /**
     * 创建人
     */
	@TableField("created_by")
	private Integer createdBy;
    /**
     * 修改时间
     */
	@TableField("modified_date")
	private Date modifiedDate;
    /**
     * 修改人
     */
	@TableField("modified_by")
	private Integer modifiedBy;

	@TableField(exist = false)
	private String name;

	@TableField(exist = false)
	private String machineCode;

	@TableField(exist=false)
	private String person;




	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getStrongVoltage() {
		return strongVoltage;
	}

	public void setStrongVoltage(Integer strongVoltage) {
		this.strongVoltage = strongVoltage;
	}

	public Integer getWeakOneVoltage() {
		return weakOneVoltage;
	}

	public void setWeakOneVoltage(Integer weakOneVoltage) {
		this.weakOneVoltage = weakOneVoltage;
	}

	public Integer getWeakTwoVoltage() {
		return weakTwoVoltage;
	}

	public void setWeakTwoVoltage(Integer weakTwoVoltage) {
		this.weakTwoVoltage = weakTwoVoltage;
	}

	public Integer getStrongTime() {
		return strongTime;
	}

	public void setStrongTime(Integer strongTime) {
		this.strongTime = strongTime;
	}

	public Integer getWeakTime() {
		return weakTime;
	}

	public void setWeakTime(Integer weakTime) {
		this.weakTime = weakTime;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MachinePhysicalProbability{" +
			"id=" + id +
			", dollId=" + dollId +
			", strongVoltage=" + strongVoltage +
			", weakOneVoltage=" + weakOneVoltage +
			", weakTwoVoltage=" + weakTwoVoltage +
			", strongTime=" + strongTime +
			", weakTime=" + weakTime +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			", name=" + name +
			", person=" + person +
			", machineCode=" + machineCode +

			"}";
	}
}
