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
@TableName("t_doll")
public class TDoll extends Model<TDoll> {

    private static final long serialVersionUID = 1L;

    /**
     * 娃娃对应的表，一个娃娃对应一个娃娃
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private Integer quantity;
	private Integer price;
	@TableField("redeem_coins")
	private Integer redeemCoins;
    /**
     * 娃娃机当前状态
     */
	@TableField("machine_status")
	private String machineStatus;
    /**
     * 机器序列号
     */
	@TableField("machine_serial_num")
	private String machineSerialNum;
	@TableField("machine_ip")
	private String machineIp;
	@TableField("machine_url")
	private String machineUrl;
	@TableField("tbimg_context_path")
	private String tbimgContextPath;
	@TableField("tbimg_file_name")
	private String tbimgFileName;
	@TableField("tbimg_real_path")
	private String tbimgRealPath;
	@TableField("created_date")
	private Date createdDate;
	@TableField("created_by")
	private Integer createdBy;
    /**
     * 后台修改时间
     */
	@TableField("modified_date")
	private Date modifiedDate;
	@TableField("modified_by")
	private Integer modifiedBy;
	@TableField("rtmp_url_1")
	private String rtmpUrl1;
	@TableField("rtmp_url_2")
	private String rtmpUrl2;
	@TableField("rtmp_url_3")
	private String rtmpUrl3;
	@TableField("rtmp_push_url")
	private String rtmpPushUrl;
	@TableField("mns_topic_name")
	private String mnsTopicName;
    /**
     * 娃娃机正在关注人数
     */
	@TableField("watching_number")
	private Integer watchingNumber;
    /**
     * 娃娃机游戏时长，单位秒
     */
	private Integer timeout;
    /**
     * 七牛连麦房间名称
     */
	@TableField("pili_room_name")
	private String piliRoomName;
    /**
     * 娃娃机识别码
     */
	@TableField("machine_code")
	private String machineCode;
    /**
     * 娃娃的识别码
     */
	@TableField("doll_ID")
	private String dollID;
    /**
     * 娃娃机类型0为普通房1为练习房
     */
	@TableField("machine_type")
	private Integer machineType;
    /**
     * 娃娃机地址id
     */
	@TableField("doll_address_id")
	private Integer dollAddressId;
    /**
     * 删除状态（1未删除 0已删除）
     */
	@TableField("delete_status")
	private Integer deleteStatus;
	
	@TableField(exist=false)
    private String province;//省
	@TableField(exist=false)
    private String city;//市
	@TableField(exist=false)
    private String county;//区
	@TableField(exist=false)
    private String street;//街道
	@TableField(exist=false)
    private String person;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getRedeemCoins() {
		return redeemCoins;
	}

	public void setRedeemCoins(Integer redeemCoins) {
		this.redeemCoins = redeemCoins;
	}

	public String getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}

	public String getMachineSerialNum() {
		return machineSerialNum;
	}

	public void setMachineSerialNum(String machineSerialNum) {
		this.machineSerialNum = machineSerialNum;
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

	public String getMachineUrl() {
		return machineUrl;
	}

	public void setMachineUrl(String machineUrl) {
		this.machineUrl = machineUrl;
	}

	public String getTbimgContextPath() {
		return tbimgContextPath;
	}

	public void setTbimgContextPath(String tbimgContextPath) {
		this.tbimgContextPath = tbimgContextPath;
	}

	public String getTbimgFileName() {
		return tbimgFileName;
	}

	public void setTbimgFileName(String tbimgFileName) {
		this.tbimgFileName = tbimgFileName;
	}

	public String getTbimgRealPath() {
		return tbimgRealPath;
	}

	public void setTbimgRealPath(String tbimgRealPath) {
		this.tbimgRealPath = tbimgRealPath;
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

	public String getRtmpUrl1() {
		return rtmpUrl1;
	}

	public void setRtmpUrl1(String rtmpUrl1) {
		this.rtmpUrl1 = rtmpUrl1;
	}

	public String getRtmpUrl2() {
		return rtmpUrl2;
	}

	public void setRtmpUrl2(String rtmpUrl2) {
		this.rtmpUrl2 = rtmpUrl2;
	}

	public String getRtmpUrl3() {
		return rtmpUrl3;
	}

	public void setRtmpUrl3(String rtmpUrl3) {
		this.rtmpUrl3 = rtmpUrl3;
	}

	public String getRtmpPushUrl() {
		return rtmpPushUrl;
	}

	public void setRtmpPushUrl(String rtmpPushUrl) {
		this.rtmpPushUrl = rtmpPushUrl;
	}

	public String getMnsTopicName() {
		return mnsTopicName;
	}

	public void setMnsTopicName(String mnsTopicName) {
		this.mnsTopicName = mnsTopicName;
	}

	public Integer getWatchingNumber() {
		return watchingNumber;
	}

	public void setWatchingNumber(Integer watchingNumber) {
		this.watchingNumber = watchingNumber;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getPiliRoomName() {
		return piliRoomName;
	}

	public void setPiliRoomName(String piliRoomName) {
		this.piliRoomName = piliRoomName;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getDollID() {
		return dollID;
	}

	public void setDollID(String dollID) {
		this.dollID = dollID;
	}

	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}

	public Integer getDollAddressId() {
		return dollAddressId;
	}

	public void setDollAddressId(Integer dollAddressId) {
		this.dollAddressId = dollAddressId;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
		return "TDoll{" +
			"id=" + id +
			", name=" + name +
			", description=" + description +
			", quantity=" + quantity +
			", price=" + price +
			", redeemCoins=" + redeemCoins +
			", machineStatus=" + machineStatus +
			", machineSerialNum=" + machineSerialNum +
			", machineIp=" + machineIp +
			", machineUrl=" + machineUrl +
			", tbimgContextPath=" + tbimgContextPath +
			", tbimgFileName=" + tbimgFileName +
			", tbimgRealPath=" + tbimgRealPath +
			", createdDate=" + createdDate +
			", createdBy=" + createdBy +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			", rtmpUrl1=" + rtmpUrl1 +
			", rtmpUrl2=" + rtmpUrl2 +
			", rtmpUrl3=" + rtmpUrl3 +
			", rtmpPushUrl=" + rtmpPushUrl +
			", mnsTopicName=" + mnsTopicName +
			", watchingNumber=" + watchingNumber +
			", timeout=" + timeout +
			", piliRoomName=" + piliRoomName +
			", machineCode=" + machineCode +
			", dollID=" + dollID +
			", machineType=" + machineType +
			", dollAddressId=" + dollAddressId +
			", deleteStatus=" + deleteStatus +
			"}";
	}
}
