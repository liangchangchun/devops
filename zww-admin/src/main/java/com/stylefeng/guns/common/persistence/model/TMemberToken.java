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
 * @author 孔欢欢
 * @since 2018-02-24
 */
@TableName("t_member_token")
public class TMemberToken extends Model<TMemberToken> {

    private static final long serialVersionUID = 1L;

	private String token;
	@TableField("member_id")
	private Integer memberId;
	@TableField("valid_start_date")
	private Date validStartDate;
	@TableField("valid_end_date")
	private Date validEndDate;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Date getValidStartDate() {
		return validStartDate;
	}

	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}

	public Date getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.token;
	}

	@Override
	public String toString() {
		return "TMemberToken{" +
			"token=" + token +
			", memberId=" + memberId +
			", validStartDate=" + validStartDate +
			", validEndDate=" + validEndDate +
			"}";
	}
}
