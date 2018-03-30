package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Author: mwan
 * Version: 1.1
 * Date: 2017/09/26
 * Description: 娃娃抓取成功后的订单持久层.
 * Copyright (c) 2017 伴飞网络. All rights reserved.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DollOrder implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2324824494383953969L;
	private Integer id;
    private String orderNumber;
    private Date orderDate;
    private Integer orderBy;
    private String status;
    private Date stockValidDate;
    private Date deliverDate;
    private String deliverMethod;
    private String deliverNumber;
    private BigDecimal deliverAmount;
    private Integer deliverCoins;
    private MemberAddr memberAddress;
    private Date modifiedDate;
    private Integer modifiedBy;
    private Integer[] orderIds;
    private String comment;
    private int dollRedeemCoins;
    private DollOrderItem orderItems;

}
