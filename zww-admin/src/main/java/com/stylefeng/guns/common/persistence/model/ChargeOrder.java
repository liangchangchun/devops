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
 * @since 2018-01-02
 */
@TableName("charge_order")
public class ChargeOrder extends Model<ChargeOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
	 * 订单编号
	 */
	@TableField("order_no")
	private String orderNo;
    /**
     * 充值规则id
     */
	private Integer chargeruleid;
    /**
     * 充值规则名称
     */
	@TableField("charge_name")
	private String chargeName;
    /**
     * 充值金额
     */
	private BigDecimal price;
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
     * 充值类型
     */
	@TableField("charge_type")
	private String chargeType;
    /**
     * 订单状态
     */
	@TableField("charge_state")
	private Integer chargeState;
    /**
     * 充值前娃娃币
     */
	@TableField("coins_before")
	private Integer coinsBefore;
    /**
     * 充值后娃娃币
     */
	@TableField("coins_after")
	private Integer coinsAfter;
    /**
     * 充值娃娃币
     */
	@TableField("coins_charge")
	private Integer coinsCharge;
	@TableField("coins_offer")
	private Integer coinsOffer;
    /**
     * 订单创建时间
     */
	@TableField("create_date")
	private Date createDate;
    /**
     * 订单更新时间
     */
	@TableField("update_date")
	private Date updateDate;

	@TableField(exist=false)
	private String registerChannel; //注册渠道号

	@TableField(exist=false)
	private String loginChannel;    //登录渠道号

	@TableField(exist=false)
	private String memberNum;    //用户编号


	public String getRegisterChannel() {
		return registerChannel;
	}

	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
	}

	public String getLoginChannel() {
		return loginChannel;
	}

	public void setLoginChannel(String loginChannel) {
		this.loginChannel = loginChannel;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getChargeruleid() {
		return chargeruleid;
	}

	public void setChargeruleid(Integer chargeruleid) {
		this.chargeruleid = chargeruleid;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public Integer getChargeState() {
		return chargeState;
	}

	public void setChargeState(Integer chargeState) {
		this.chargeState = chargeState;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ChargeOrder{" +
			"id=" + id +
			", orderNo=" + orderNo +
			", chargeruleid=" + chargeruleid +
			", chargeName=" + chargeName +
			", price=" + price +
			", memberId=" + memberId +
			", memberName=" + memberName +
			", chargeType=" + chargeType +
			", chargeState=" + chargeState +
			", coinsBefore=" + coinsBefore +
			", coinsAfter=" + coinsAfter +
			", coinsCharge=" + coinsCharge +
			", coinsOffer=" + coinsOffer +
			", createDate=" + createDate +
			", updateDate=" + updateDate +
			", registerChannel=" + registerChannel +
			", loginChannel=" + loginChannel +
			", memberNum=" + memberNum +
			"}";
	}
}
