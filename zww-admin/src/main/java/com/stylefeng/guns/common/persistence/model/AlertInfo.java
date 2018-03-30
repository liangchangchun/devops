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
 * @since 2018-02-06
 */
@TableName("alert_info")
public class AlertInfo extends Model<AlertInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 弹窗图片URL
     */
	@TableField("alert_img_url")
	private String alertImgUrl;
    /**
     * 描述
     */
	private String description;
    /**
     * 超链接
     */
	private String hyperlink;
    /**
     * 链接类型
     */
	@TableField("link_type")
	private Integer linkType;
    /**
     * 排序
     */
	private Integer sorts;
    /**
     * 创建时间
     */
	@TableField("created_date")
	private Date createdDate;
    /**
     * 创建人
     */
	@TableField("created_by")
	private Integer createdBy;
    /**
     * 修改时间
     */
	@TableField("modified_date")
	private Date modifiedDate;
    /**
     * 修改人
     */
	@TableField("modified_by")
	private Integer modifiedBy;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlertImgUrl() {
		return alertImgUrl;
	}

	public void setAlertImgUrl(String alertImgUrl) {
		this.alertImgUrl = alertImgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AlertInfo{" +
			"id=" + id +
			", alertImgUrl=" + alertImgUrl +
			", description=" + description +
			", hyperlink=" + hyperlink +
			", linkType=" + linkType +
			", sorts=" + sorts +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
