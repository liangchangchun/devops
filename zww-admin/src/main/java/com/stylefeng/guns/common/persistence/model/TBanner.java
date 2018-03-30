package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

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
 * @author liangchangchun
 * @since 2018-01-18
 */
@TableName("t_banner")
public class TBanner extends Model<TBanner> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String description;
	@TableField("image_url")
	private String imageUrl;
    /**
     * 超链接
     */
	private String hyperlink;
    /**
     * 是否生效
     */
	@TableField("active_flg")
	private Boolean activeFlg;
	private String type;
    /**
     * 排序
     */
	private Integer sorts;
	@TableField("created_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createdDate;
	@TableField("created_by")
	private Integer createdBy;
	@TableField("modified_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date modifiedDate;
	@TableField("modified_by")
	private Integer modifiedBy;
	@TableField("valid_start_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date validStartDate;
	@TableField("valid_end_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date validEndDate;
    /**
     * 超链接状态（0链接，1支付页，2.原生，3，支付）
     */
	@TableField("link_type")
	private Integer linkType;
	
	@TableField(exist = false)
	private String linkTypeName;
    /**
     * 支付第几个套餐
     */
	@TableField("pay_index")
	private Integer payIndex;
	@TableField(exist = false)
	private String payIndexName;


	@TableField("qq_group_num")
	private String qqGroupNum;

	@TableField("qq_group_key")
	private String qqGroupKey;

	@TableField("package_name")
	private String packageName;


	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getQqGroupNum() {
		return qqGroupNum;
	}

	public void setQqGroupNum(String qqGroupNum) {
		this.qqGroupNum = qqGroupNum;
	}


	public String getQqGroupKey() {
		return qqGroupKey;
	}

	public void setQqGroupKey(String qqGroupKey) {
		this.qqGroupKey = qqGroupKey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public Boolean getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(Boolean activeFlg) {
		this.activeFlg = activeFlg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
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

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Integer getPayIndex() {
		return payIndex;
	}

	public void setPayIndex(Integer payIndex) {
		this.payIndex = payIndex;
	}
	

	public String getLinkTypeName() {
		return linkTypeName;
	}

	public void setLinkTypeName(String linkTypeName) {
		this.linkTypeName = linkTypeName;
	}

	public String getPayIndexName() {
		return payIndexName;
	}

	public void setPayIndexName(String payIndexName) {
		this.payIndexName = payIndexName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TBanner{" +
			"id=" + id +
			", description=" + description +
			", imageUrl=" + imageUrl +
			", hyperlink=" + hyperlink +
			", activeFlg=" + activeFlg +
			", type=" + type +
			", sorts=" + sorts +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			", validStartDate=" + validStartDate +
			", validEndDate=" + validEndDate +
			", linkType=" + linkType +
			", payIndex=" + payIndex +
			", qqGroupNum=" + qqGroupNum +
			", qqGroupKey=" + qqGroupKey +
			", packageName=" + packageName +
			"}";
	}
}
