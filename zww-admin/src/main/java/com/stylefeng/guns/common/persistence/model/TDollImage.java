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
 * @author liangchangchun
 * @since 2018-01-24
 */
@TableName("t_doll_image")
public class TDollImage extends Model<TDollImage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("doll_id")
	private Integer dollId;
	@TableField("img_context_path")
	private String imgContextPath;
	@TableField("img_file_name")
	private String imgFileName;
	@TableField("img_real_path")
	private String imgRealPath;
	@TableField("created_date")
	private Date createdDate;
	@TableField("created_by")
	private Integer createdBy;
	@TableField("modified_date")
	private Date modifiedDate;
	@TableField("modified_by")
	private Integer modifiedBy;
	@TableField(exist=false)
	private String name;
	/**
	 * 操作人
	 * @return
	 */
	@TableField(exist=false)
	private String person;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDollId() {
		return dollId;
	}

	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}

	public String getImgContextPath() {
		return imgContextPath;
	}

	public void setImgContextPath(String imgContextPath) {
		this.imgContextPath = imgContextPath;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgRealPath() {
		return imgRealPath;
	}

	public void setImgRealPath(String imgRealPath) {
		this.imgRealPath = imgRealPath;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TDollImage{" +
			"id=" + id +
			", dollId=" + dollId +
			", imgContextPath=" + imgContextPath +
			", imgFileName=" + imgFileName +
			", imgRealPath=" + imgRealPath +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
