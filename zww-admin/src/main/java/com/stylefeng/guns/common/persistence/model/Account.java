package com.stylefeng.guns.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
@TableName("account")
public class Account extends Model<Account> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 注册用户（玩家）
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id; //userid
	@TableField("coins")
	private Integer coins;//金币
	@TableField("superTicket")
	private Integer superTicket;//钻石
	@TableField("weeksCardState")
	private Date weeksCardState;//周卡状态
	@TableField("monthCardState")
	private Date monthCardState;//月卡状态
	@TableField("bitState")
	private Integer bitState;//用户状态码
	@TableField("growth_value")
	private double growthValue;//用户等级成长值

	@TableField(exist=false)
	private String addReason;//加币原因


	public double getGrowthValue() {
		return growthValue;
	}

	public void setGrowthValue(double growthValue) {
		this.growthValue = growthValue;
	}

	public String getAddReason() {
		return addReason;
	}

	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoins() {
		return coins;
	}
	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getSuperTicket() {
		return superTicket;
	}

	public void setSuperTicket(Integer superTicket) {
		this.superTicket = superTicket;
	}

	public Date getWeeksCardState() {
		return weeksCardState;
	}

	public void setWeeksCardState(Date weeksCardState) {
		this.weeksCardState = weeksCardState;
	}

	public Date getMonthCardState() {
		return monthCardState;
	}

	public void setMonthCardState(Date monthCardState) {
		this.monthCardState = monthCardState;
	}

	public Integer getBitState() {
		return bitState;
	}

	public void setBitState(Integer bitState) {
		this.bitState = bitState;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Account{" +
			"id=" + id +
			", coins=" + coins +
			", superTicket=" + superTicket +
			", weeksCardState=" + weeksCardState +
			", monthCardState=" + monthCardState +
			", bitState=" + bitState +
			", addReason=" + addReason +
			", growthValue=" + growthValue +
			"}";
	}
}
