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
 * @since 2018-02-08
 */
@TableName("member_charge_combo")
public class MemberChargeCombo extends Model<MemberChargeCombo> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户id
     */
	@TableField("member_id")
	private Integer memberId;
    /**
     * 用户名称
     */
	@TableField("member_name")
	private String memberName;
    /**
     * 充值规则类型
     */
	@TableField("charge_type")
	private Integer chargeType;
    /**
     * 充值时长包名称
     */
	@TableField("charge_name")
	private String chargeName;
    /**
     * 期限
     */
	@TableField("charge_date_limit")
	private Integer chargeDateLimit;
    /**
     * 充值起始时间
     */
	@TableField("charge_date_start")
	private Date chargeDateStart;
    /**
     * 每日赠送娃娃币
     */
	@TableField("coins_give")
	private Integer coinsGive;
    /**
     * 时长包有效状态
     */
	@TableField("member_state")
	private Integer memberState;
    /**
     * 已经赠送次数
     */
	@TableField("give_times")
	private Integer giveTimes;


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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getChargeType() {
		return chargeType;
	}

	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Integer getChargeDateLimit() {
		return chargeDateLimit;
	}

	public void setChargeDateLimit(Integer chargeDateLimit) {
		this.chargeDateLimit = chargeDateLimit;
	}

	public Date getChargeDateStart() {
		return chargeDateStart;
	}

	public void setChargeDateStart(Date chargeDateStart) {
		this.chargeDateStart = chargeDateStart;
	}

	public Integer getCoinsGive() {
		return coinsGive;
	}

	public void setCoinsGive(Integer coinsGive) {
		this.coinsGive = coinsGive;
	}

	public Integer getMemberState() {
		return memberState;
	}

	public void setMemberState(Integer memberState) {
		this.memberState = memberState;
	}

	public Integer getGiveTimes() {
		return giveTimes;
	}

	public void setGiveTimes(Integer giveTimes) {
		this.giveTimes = giveTimes;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberChargeCombo{" +
			"id=" + id +
			", memberId=" + memberId +
			", memberName=" + memberName +
			", chargeType=" + chargeType +
			", chargeName=" + chargeName +
			", chargeDateLimit=" + chargeDateLimit +
			", chargeDateStart=" + chargeDateStart +
			", coinsGive=" + coinsGive +
			", memberState=" + memberState +
			", giveTimes=" + giveTimes +
			"}";
	}
}
