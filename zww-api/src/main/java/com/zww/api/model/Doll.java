package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Author: mwan
 * Version: 1.1
 * Date: 2017/09/19
 * Description: 娃娃机持久化类.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doll implements Serializable {
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    private static final int DENY = -1;
    private static final int AUDITING = 0;
    private static final int RETURNCOINS = 1;
    private static final int RETURNDOLL = 2;
    private static final int NORMAL = 3;

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
    private String machineCode;//机器号
    private String videoUrl;
    private Integer historyId;//抓取历史id
    private Integer dollAddressId;//机器地址id
    private Integer deleteStatus;//删除状态
    private String bgm;//背景音乐
    private String gameNum;
    private String rtmpUrlH5;//h5拉流地址
    private Integer checkState;//申诉状态'审核状态（待审核0，通过返币1，通过发娃娃2，未通过-1）

    public String checkStateString() {
        switch (checkState) {
            case DENY:
                return "未通过";
            case AUDITING:
                return "待审核";
            case RETURNCOINS:
                return "审核通过退还金币";
            case RETURNDOLL:
                return "审核通过补发娃娃";
            case NORMAL:
                return "未申诉";
            default:
                return "状态异常";
        }
    }
}