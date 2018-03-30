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
 * @since 2018-01-25
 */
@TableName("t_doll_order_item")
public class TDollOrderItem extends Model<TDollOrderItem> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("order_id")
	private Integer orderId;
	@TableField("doll_id")
	private Integer dollId;
	private Integer quantity;
	@TableField("created_date")
	private Date createdDate;
	@TableField("modified_date")
	private Date modifiedDate;

    /**
     * 娃娃编号
     */
	@TableField("doll_code")
	private String dollCode;

	@TableField(exist=false)
	private String dollName;//娃娃名称

	@TableField(exist=false)
	private String dollImg;//娃娃图片



	public String getDollName() {
		return dollName;
	}

	public void setDollName(String dollName) {
		this.dollName = dollName;
	}

	public String getDollImg() {
		return dollImg;
	}

	public void setDollImg(String dollImg) {
		this.dollImg = dollImg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDollId() {
		return dollId;
	}

	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public String getDollCode() {
		return dollCode;
	}

	public void setDollCode(String dollCode) {
		this.dollCode = dollCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TDollOrderItem{" +
			"id=" + id +
			", orderId=" + orderId +
			", dollId=" + dollId +
			", quantity=" + quantity +
			", createdDate=" + createdDate +
			", modifiedDate=" + modifiedDate +
			", dollCode=" + dollCode +
			", dollName=" + dollName +
			", dollCode=" + dollImg +
			"}";
	}
}
