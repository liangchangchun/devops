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
 * @since 2018-01-17
 */
@TableName("t_charge_rules")
public class TChargeRules extends Model<TChargeRules> {

    private static final long serialVersionUID = 1L;

    /**
     * 充值规则设置唯一标识
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 价格
     */
	@TableField("charge_price")
	private BigDecimal chargePrice;
    /**
     * 娃娃币
     */
	@TableField("coins_charge")
	private Integer coinsCharge;
    /**
     * 赠送娃娃币
     */
	@TableField("coins_offer")
	private Integer coinsOffer;
    /**
     * 折扣
     */
	private BigDecimal discount;
    /**
     * 说明文字
     */
	private String description;
    /**
     * 创建日期
     */
	@TableField("created_date")
	private Date createdDate;
    /**
     * 创建人id
     */
	@TableField("created_by")
	private Integer createdBy;
    /**
     * 修改日期
     */
	@TableField("modified_date")
	private Date modifiedDate;
    /**
     * 修改人id
     */
	@TableField("modified_by")
	private Integer modifiedBy;
    /**
     * 充值类型
     */
	@TableField("charge_type")
	private Integer chargeType;
    /**
     * 规则名称
     */
	@TableField("charge_name")
	private String chargeName;
    /**
     * 排序序号
     */
	private Integer orderby;
    /**
     * 首充娃娃币
     */
	@TableField("cions_first")
	private Integer cionsFirst;
    /**
     * 限购次数,-1为无限次
     */
	@TableField("charge_times_limit")
	private Integer chargeTimesLimit;
    /**
     * 期限
     */
	@TableField("charge_date_limit")
	private Integer chargeDateLimit;
    /**
     * 规则状态（1上架 0下架）
     */
	@TableField("rules_status")
	private Integer rulesStatus;
	@TableField("superTicket_charge")
	private Integer superTicketCharge;
	@TableField("superTicket_offer")
	private Integer superTicketOffer;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getChargePrice() {
		return chargePrice;
	}

	public void setChargePrice(BigDecimal chargePrice) {
		this.chargePrice = chargePrice;
	}

	public Integer getCoinsCharge() {
		return coinsCharge;
	}

	public void setCoinsCharge(Integer coinsCharge) {
		this.coinsCharge = coinsCharge;
	}

	public Integer getCoinsOffer() {
		return coinsOffer;
	}

	public void setCoinsOffer(Integer coinsOffer) {
		this.coinsOffer = coinsOffer;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public Integer getCionsFirst() {
		return cionsFirst;
	}

	public void setCionsFirst(Integer cionsFirst) {
		this.cionsFirst = cionsFirst;
	}

	public Integer getChargeTimesLimit() {
		return chargeTimesLimit;
	}

	public void setChargeTimesLimit(Integer chargeTimesLimit) {
		this.chargeTimesLimit = chargeTimesLimit;
	}

	public Integer getChargeDateLimit() {
		return chargeDateLimit;
	}

	public void setChargeDateLimit(Integer chargeDateLimit) {
		this.chargeDateLimit = chargeDateLimit;
	}

	public Integer getRulesStatus() {
		return rulesStatus;
	}

	public void setRulesStatus(Integer rulesStatus) {
		this.rulesStatus = rulesStatus;
	}

	public Integer getSuperTicketCharge() {
		return superTicketCharge;
	}

	public void setSuperTicketCharge(Integer superTicketCharge) {
		this.superTicketCharge = superTicketCharge;
	}

	public Integer getSuperTicketOffer() {
		return superTicketOffer;
	}

	public void setSuperTicketOffer(Integer superTicketOffer) {
		this.superTicketOffer = superTicketOffer;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TChargeRules{" +
			"id=" + id +
			", chargePrice=" + chargePrice +
			", coinsCharge=" + coinsCharge +
			", coinsOffer=" + coinsOffer +
			", discount=" + discount +
			", description=" + description +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			", chargeType=" + chargeType +
			", chargeName=" + chargeName +
			", orderby=" + orderby +
			", cionsFirst=" + cionsFirst +
			", chargeTimesLimit=" + chargeTimesLimit +
			", chargeDateLimit=" + chargeDateLimit +
			", rulesStatus=" + rulesStatus +
			", superTicketCharge=" + superTicketCharge +
			", superTicketOffer=" + superTicketOffer +
			"}";
	}
}
