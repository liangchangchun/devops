package com.zww.api.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Author: mwan
 * Version: 1.1
 * Date: 2018/03/26
 * Description: 娃娃机持久化类.
 * Copyright (c) 2018 zww网络. All rights reserved.
 */
public class DollAndAddress implements Serializable{

	/**
	 */
	private static final long serialVersionUID = 3244574094084147896L;

	private Integer id;

    private String name;

    private String description;
    /**
     * 娃娃机类型  0 为普通房间   1为练习币房间
     */
    private Integer machineType;

    private String dollID;

    private Integer quantity;

    private Integer price;

    private String machineStatus;

    private String machineSerialNum;

    private String machineIp;

    private String machineUrl;

    private String tbimgContextPath;

    private String tbimgFileName;

    private String tbimgRealPath;

    private Date createdDate;

    private Integer createdBy;

    private Date modifiedDate;

    private Integer modifiedBy;
    
    private String rtmpUrl1;
    
    private String rtmpUrl2;
    
    private String rtmpUrl3;
    
    private String rtmpPushUrl;
    
    private int watchingNumber;
    
    private String mnsTopicName;
    
    private String catchStatus;
    
    private Timestamp catchDate;
    
    private int memberId;
    
    private Integer redeemCoins;
    
    private Integer timeout; 
    
    private String machineCode;

    private Integer historyId;

    private Integer dollAddressId;

    private String province;//省

    private String city;//市

    private String county;//区

    private String street;//街道


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

    public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public Integer getRedeemCoins() {
		return redeemCoins;
	}

	public void setRedeemCoins(Integer redeemCoins) {
		this.redeemCoins = redeemCoins;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getCatchStatus() {
		return catchStatus;
	}

	public void setCatchStatus(String catchStatus) {
		this.catchStatus = catchStatus;
	}

	public Timestamp getCatchDate() {
		return catchDate;
	}

	public void setCatchDate(Timestamp catchDate) {
		this.catchDate = catchDate;
	}

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
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public String getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(String machineStatus) {
        this.machineStatus = machineStatus == null ? null : machineStatus.trim();
    }

    public String getMachineSerialNum() {
        return machineSerialNum;
    }

    public void setMachineSerialNum(String machineSerialNum) {
        this.machineSerialNum = machineSerialNum == null ? null : machineSerialNum.trim();
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp == null ? null : machineIp.trim();
    }

    public String getMachineUrl() {
        return machineUrl;
    }

    public void setMachineUrl(String machineUrl) {
        this.machineUrl = machineUrl == null ? null : machineUrl.trim();
    }

    public String getTbimgContextPath() {
        return tbimgContextPath;
    }

    public void setTbimgContextPath(String tbimgContextPath) {
        this.tbimgContextPath = tbimgContextPath == null ? null : tbimgContextPath.trim();
    }

    public String getTbimgFileName() {
        return tbimgFileName;
    }

    public void setTbimgFileName(String tbimgFileName) {
        this.tbimgFileName = tbimgFileName == null ? null : tbimgFileName.trim();
    }

    public String getTbimgRealPath() {
        return tbimgRealPath;
    }

    public void setTbimgRealPath(String tbimgRealPath) {
        this.tbimgRealPath = tbimgRealPath == null ? null : tbimgRealPath.trim();
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

	public int getWatchingNumber() {
		return watchingNumber;
	}

	public void setWatchingNumber(int watchingNumber) {
		this.watchingNumber = watchingNumber;
	}

	public String getMnsTopicName() {
		return mnsTopicName;
	}

	public void setMnsTopicName(String mnsTopicName) {
		this.mnsTopicName = mnsTopicName;
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

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	

	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}

    public String getDollID() {
        return dollID;
    }

    public void setDollID(String dollID) {
        this.dollID = dollID;
    }

    public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

    public Integer getDollAddressId() {
        return dollAddressId;
    }

    public void setDollAddressId(Integer dollAddressId) {
        this.dollAddressId = dollAddressId;
    }

    @Override
	public String toString() {
		return "Doll [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + ", machineStatus=" + machineStatus + ", machineSerialNum=" + machineSerialNum
				+ ", machineIp=" + machineIp + ", machineUrl=" + machineUrl + ", tbimgContextPath=" + tbimgContextPath
				+ ", tbimgFileName=" + tbimgFileName + ", tbimgRealPath=" + tbimgRealPath + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate + ", modifiedBy="
				+ modifiedBy + ", rtmpUrl1=" + rtmpUrl1 + ", rtmpUrl2=" + rtmpUrl2 + ", rtmpUrl3=" + rtmpUrl3
				+ ", rtmpPushUrl=" + rtmpPushUrl + ", watchingNumber=" + watchingNumber + ", mnsTopicName="
				+ mnsTopicName + ", catchStatus=" + catchStatus + ", catchDate=" + catchDate + ", memberId=" + memberId
				+ ", redeemCoins=" + redeemCoins + ", timeout=" + timeout + ",machineType="+machineType+",dollID="+dollID
                +",dollAddressId="+dollAddressId+",province="+province+",city="+city+",county="+county+",street="+street+"]";
	}
	
	
}