package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2018-01-03
 */
@TableName("t_member_charge_history")
public class MemberChargeHistory extends Model<MemberChargeHistory> {

    private static final long serialVersionUID = 1L;

    /**
     * 玩家充值记录
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("member_id")
	private Integer memberId;
	@TableField("prepaid_amt")
	private BigDecimal prepaidAmt;
	private Integer coins;
	@TableField("charge_date")
	private Date chargeDate;
	private String type;
	@TableField("charge_method")
	private String chargeMethod;
	@TableField("doll_id")
	private Integer dollId;
	@TableField("coins_before")
	private Integer coinsBefore;
	@TableField("coins_after")
	private Integer coinsAfter;

	@TableField(exist=false)
	private Integer coinsSum;//消耗，获取币数
	@TableField(exist=false)
	private Integer superTicket; //强爪券数
	@TableField(exist=false)
	private Integer superTicketSum;//总强爪券数


	public Integer getSuperTicket() {
		return superTicket;
	}

	public void setSuperTicket(Integer superTicket) {
		this.superTicket = superTicket;
	}

	public Integer getSuperTicketSum() {
		return superTicketSum;
	}

	public void setSuperTicketSum(Integer superTicketSum) {
		this.superTicketSum = superTicketSum;
	}

	public Integer getCoinsSum() {
		return coinsSum;
	}

	public void setCoinsSum(Integer coinsSum) {
		this.coinsSum = coinsSum;
	}

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

	public BigDecimal getPrepaidAmt() {
		return prepaidAmt;
	}

	public void setPrepaidAmt(BigDecimal prepaidAmt) {
		this.prepaidAmt = prepaidAmt;
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public Integer getDollId() {
		return dollId;
	}

	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}

	public Integer getCoinsBefore() {
		return coinsBefore;
	}

	public void setCoinsBefore(Integer coinsBefore) {
		this.coinsBefore = coinsBefore;
	}

	public Integer getCoinsAfter() {
		return coinsAfter;
	}

	public void setCoinsAfter(Integer coinsAfter) {
		this.coinsAfter = coinsAfter;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberChargeHistory{" +
			"id=" + id +
			", memberId=" + memberId +
			", prepaidAmt=" + prepaidAmt +
			", coins=" + coins +
			", chargeDate=" + chargeDate +
			", type=" + type +
			", chargeMethod=" + chargeMethod +
			", dollId=" + dollId +
			", coinsBefore=" + coinsBefore +
			", coinsAfter=" + coinsAfter +
			", coinsSum=" + coinsSum +
			", superTicket=" + superTicket +
			", superTicketSum=" + superTicketSum +
			"}";
	}
}
