package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2018-03-28
 */
@TableName("member_vip")
public class MemberVip extends Model<MemberVip> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 等级
     */
	private Integer level;
    /**
     * VIP名称
     */
	private String name;
    /**
     * 最低额度
     */
	@TableField("least_allowed")
	private BigDecimal leastAllowed;
    /**
     * 包邮个数
     */
	@TableField("exemption_postage_number")
	private Integer exemptionPostageNumber;
    /**
     * 发货时效
     */
	@TableField("delivery_time")
	private Integer deliveryTime;
    /**
     * 寄存时效
     */
	@TableField("check_time")
	private Integer checkTime;
    /**
     * 闪电申诉
     */
	@TableField("flash_appeal")
	private Integer flashAppeal;
    /**
     * 充值折扣
     */
	private BigDecimal discount;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLeastAllowed() {
		return leastAllowed;
	}

	public void setLeastAllowed(BigDecimal leastAllowed) {
		this.leastAllowed = leastAllowed;
	}

	public Integer getExemptionPostageNumber() {
		return exemptionPostageNumber;
	}

	public void setExemptionPostageNumber(Integer exemptionPostageNumber) {
		this.exemptionPostageNumber = exemptionPostageNumber;
	}

	public Integer getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Integer checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getFlashAppeal() {
		return flashAppeal;
	}

	public void setFlashAppeal(Integer flashAppeal) {
		this.flashAppeal = flashAppeal;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberVip{" +
			"id=" + id +
			", level=" + level +
			", name=" + name +
			", leastAllowed=" + leastAllowed +
			", exemptionPostageNumber=" + exemptionPostageNumber +
			", deliveryTime=" + deliveryTime +
			", checkTime=" + checkTime +
			", flashAppeal=" + flashAppeal +
			", discount=" + discount +
			"}";
	}
}
