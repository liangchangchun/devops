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
 * @since 2018-01-29
 */
@TableName("machine_probability")
public class MachineProbability extends Model<MachineProbability> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 概率规则id
     */
	@TableField("probability_rules_id")
	private Integer probabilityRulesId;
    /**
     * 机器id
     */
	@TableField("doll_id")
	private Integer dollId;
    /**
     * 概率1
     */
	@TableField("probability_1")
	private Double probability1;
    /**
     * 概率2
     */
	@TableField("probability_2")
	private Double probability2;
    /**
     * 概率3
     */
	@TableField("probability_3")
	private Double probability3;
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
     * 创建人
     */
	@TableField("created_by")
	private Integer createdBy;
    /**
     * 修改人
     */
	@TableField("modified_by")
	private Integer modifiedBy;

	@TableField("base_num")
	private Integer baseNum;

	@TableField(exist=false)
	private String dollName;//机器名
	@TableField(exist=false)
	private String machainCode;//机器号

	@TableField(exist=false)
	private String person;//机器号


	public Integer getBaseNum() {
		return baseNum;
	}

	public void setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getMachainCode() {
		return machainCode;
	}

	public void setMachainCode(String machainCode) {
		this.machainCode = machainCode;
	}

	public String getDollName() {
		return dollName;
	}

	public void setDollName(String dollName) {
		this.dollName = dollName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProbabilityRulesId() {
		return probabilityRulesId;
	}

	public void setProbabilityRulesId(Integer probabilityRulesId) {
		this.probabilityRulesId = probabilityRulesId;
	}

	public Integer getDollId() {
		return dollId;
	}

	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}

	public Double getProbability1() {
		return probability1;
	}

	public void setProbability1(Double probability1) {
		this.probability1 = probability1;
	}

	public Double getProbability2() {
		return probability2;
	}

	public void setProbability2(Double probability2) {
		this.probability2 = probability2;
	}

	public Double getProbability3() {
		return probability3;
	}

	public void setProbability3(Double probability3) {
		this.probability3 = probability3;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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
		return "MachineProbability{" +
			"id=" + id +
			", probabilityRulesId=" + probabilityRulesId +
			", dollId=" + dollId +
			", probability1=" + probability1 +
			", probability2=" + probability2 +
			", probability3=" + probability3 +
			", createdDate=" + createdDate +
			", modifiedDate=" + modifiedDate +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			", dollName=" + dollName +
			", machainCode=" + machainCode +
			", person=" + person +
			", baseNum=" + baseNum +
			"}";
	}
}
