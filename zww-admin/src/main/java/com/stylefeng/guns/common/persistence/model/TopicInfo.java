package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author konghuanhuan
 * @since 2018-01-30
 */
@TableName("topic_info")
public class TopicInfo extends Model<TopicInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主题Id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 主题类型
     */
	private Integer topicType;
    /**
     * 主题名称
     */
	private String topicName;
    /**
     * 主题详情
     */
	private String details;
	private Integer orderBy;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTopicType() {
		return topicType;
	}

	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TopicInfo{" +
			"id=" + id +
			", topicType=" + topicType +
			", topicName=" + topicName +
			", details=" + details +
			", orderBy=" + orderBy +
			"}";
	}
}
