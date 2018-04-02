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
 * @author 孔欢欢
 * @since 2018-04-02
 */
@TableName("doll_info")
public class DollInfo extends Model<DollInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String dollName;
	private Integer dollTotal;
	@TableField("img_url")
	private String imgUrl;
	private Date addTime;
	private String dollCode;
	private String size;
	private String type;
	private String note;
	private Integer redeemCoins;

	public Integer getRedeemCoins() {
		return redeemCoins;
	}

	public void setRedeemCoins(Integer redeemCoins) {
		this.redeemCoins = redeemCoins;
	}

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

	public Integer getDollTotal() {
		return dollTotal;
	}

	public void setDollTotal(Integer dollTotal) {
		this.dollTotal = dollTotal;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getDollCode() {
		return dollCode;
	}

	public void setDollCode(String dollCode) {
		this.dollCode = dollCode;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DollInfo{" +
			"id=" + id +
			", dollName=" + dollName +
			", dollTotal=" + dollTotal +
			", imgUrl=" + imgUrl +
			", addTime=" + addTime +
			", dollCode=" + dollCode +
			", size=" + size +
			", type=" + type +
			", note=" + note +
			", redeemCoins=" + redeemCoins +
			"}";
	}
}
