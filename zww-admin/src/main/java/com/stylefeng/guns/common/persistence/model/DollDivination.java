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
 * @since 2018-02-09
 */
@TableName("doll_divination")
public class DollDivination extends Model<DollDivination> {

    private static final long serialVersionUID = 1L;

	@TableId("id")
	private Integer id;
    @TableField("doll_id")
	private Integer dollId;
	@TableField("divination_id")
	private Integer divinationId;

	@TableField(exist=false)
	private String dollName;//机器名

	@TableField(exist=false)
	private String dollCode;//机器号

	@TableField(exist=false)
	private String dollUrl;//机器图

	@TableField(exist=false)
	private String divinationName;//占卜名


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDollName() {
		return dollName;
	}

	public void setDollName(String dollName) {
		this.dollName = dollName;
	}

	public String getDollCode() {
		return dollCode;
	}

	public void setDollCode(String dollCode) {
		this.dollCode = dollCode;
	}

	public String getDollUrl() {
		return dollUrl;
	}

	public void setDollUrl(String dollUrl) {
		this.dollUrl = dollUrl;
	}

	public String getDivinationName() {
		return divinationName;
	}

	public void setDivinationName(String divinationName) {
		this.divinationName = divinationName;
	}

	public Integer getDollId() {
		return dollId;
	}

	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}

	public Integer getDivinationId() {
		return divinationId;
	}

	public void setDivinationId(Integer divinationId) {
		this.divinationId = divinationId;
	}

	@Override
	protected Serializable pkVal() {
		return this.dollId;
	}

	@Override
	public String toString() {
		return "DollDivination{" +
			"id=" + id +
			"dollId=" + dollId +
			", divinationId=" + divinationId +
			", dollName=" + dollName +
			", dollCode=" + dollCode +
			", dollUrl=" + dollUrl +
			", divinationName=" + divinationName +
			"}";
	}
}
