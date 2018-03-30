package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import com.zww.common.Enviroment;

/**
 * Author: perry
 * Version: 1.0
 * Date: 2017/09/25
 * Description: 收货地址实体类.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer memberId;
    private String receiverName;
    private String receiverPhone;
    private String province;
    private String city;
    private String county;
    private String street;
    private Date createdDate;
    private Date modifiedDate;
    private int defaultFlg1;
    private boolean defaultFlg;

    public int getDefaultFlg1() {
        if (this.isDefaultFlg()) {
            return Enviroment.ISDEFAULTFLG;
        } else {
            return Enviroment.NOTDEFAULTFLG;
        }
    }
}