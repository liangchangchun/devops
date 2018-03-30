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
@TableName("divination_topic")
public class DivinationTopic extends Model<DivinationTopic> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 预测名称
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

	@TableField("result_status")
	private Integer resultStatus;//预测结果状态（0每次不同 1每日一变 2不变）

	@TableField("modeUrl")
	private String modeUrl;//模板图片
	@TableField("wxpireTime")
	private Integer wxpireTime;//过期时间


	public String getModeUrl() {
		return modeUrl;
	}

	public void setModeUrl(String modeUrl) {
		this.modeUrl = modeUrl;
	}

	public Integer getWxpireTime() {
		return wxpireTime;
	}

	public void setWxpireTime(Integer wxpireTime) {
		this.wxpireTime = wxpireTime;
	}

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "DivinationTopic{" +
			"id=" + id +
			", divinationName=" + divinationName +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			", resultStatus=" + resultStatus +
			", modeUrl=" + modeUrl +
			", wxpireTime=" + wxpireTime +
			"}";
	}
}
