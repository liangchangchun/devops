package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("doll_topic")
public class DollTopic extends Model<DollTopic> {

    private static final long serialVersionUID = 1L;

    /**
     * 主题id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 娃娃机序号
     */
	@TableField("doll_id")
	private Integer dollId;
    /**
     * 娃娃机名称
     */
	@TableField("doll_name")
	private String dollName;
    /**
     * 房间类型
     */
	@TableField("room_type")
	private Integer roomType;
    /**
     * 主题类型
     */
	@TableField("topic_type")
	private Integer topicType;
    /**
     * 主题名称
     */
	@TableField("topic_name")
	private String topicName;
	 /**
     * 机器编号
     */
	@TableField(exist=false)
	private String machineCode;
    /**
     * 房间分组
     */
	private Integer groupid;


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

	public String getDollName() {
		return dollName;
	}

	public void setDollName(String dollName) {
		this.dollName = dollName;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
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

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DollTopic{" +
			"id=" + id +
			", dollId=" + dollId +
			", dollName=" + dollName +
			", roomType=" + roomType +
			", topicType=" + topicType +
			", topicName=" + topicName +
			", groupid=" + groupid +
			"}";
	}
}
