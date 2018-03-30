package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
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
 * @since 2018-01-17
 */
@TableName("t_system_pref")
public class TSystemPref extends Model<TSystemPref> {

    private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private String value;
	@TableField("modified_date")
	private Date modifiedDate;
	@TableField("modified_by")
	private Integer modifiedBy;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		return this.code;
	}

	@Override
	public String toString() {
		return "TSystemPref{" +
			"code=" + code +
			", name=" + name +
			", value=" + value +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
