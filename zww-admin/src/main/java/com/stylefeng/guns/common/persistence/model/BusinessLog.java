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
 * @since 2018-03-20
 */
@TableName("business_log")
public class BusinessLog extends Model<BusinessLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 日志类型
     */
	@TableField("log_type")
	private String logType;
    /**
     * 日志名称
     */
	@TableField("log_name")
	private String logName;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 类名
     */
	@TableField("class_name")
	private String className;
    /**
     * 方法名
     */
	private String method;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 是否成功
     */
	private String succeed;
    /**
     * 日志详情
     */
	private String message;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BusinessLog{" +
			"id=" + id +
			", logType=" + logType +
			", logName=" + logName +
			", userId=" + userId +
			", className=" + className +
			", method=" + method +
			", createTime=" + createTime +
			", succeed=" + succeed +
			", message=" + message +
			"}";
	}
}
