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
 * @since 2018-02-02
 */
@TableName("divination_image")
public class DivinationImage extends Model<DivinationImage> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 占卜主题图片
     */
	@TableField("divination_topic_img")
	private String divinationTopicImg;
    /**
     * 占卜主题id
     */
	@TableField("divination_topic_id")
	private Integer divinationTopicId;
    /**
     * 占卜主题名称
     */
	@TableField("divination_name")
	private String divinationName;
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

	public String getDivinationTopicImg() {
		return divinationTopicImg;
	}

	public void setDivinationTopicImg(String divinationTopicImg) {
		this.divinationTopicImg = divinationTopicImg;
	}

	public Integer getDivinationTopicId() {
		return divinationTopicId;
	}

	public void setDivinationTopicId(Integer divinationTopicId) {
		this.divinationTopicId = divinationTopicId;
	}

	public String getDivinationName() {
		return divinationName;
	}

	public void setDivinationName(String divinationName) {
		this.divinationName = divinationName;
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
		return "DivinationImage{" +
			"id=" + id +
			", divinationTopicImg=" + divinationTopicImg +
			", divinationTopicId=" + divinationTopicId +
			", divinationName=" + divinationName +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
