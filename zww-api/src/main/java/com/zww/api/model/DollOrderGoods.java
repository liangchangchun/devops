package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 申请发货订单
 * @author Administrator
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DollOrderGoods implements Serializable {

	private static final long serialVersionUID = 3242653498932276011L;
	
	private Integer id;

    private String orderNumber;
    /**
	 * 订单创建日期
	 */
    private Date orderDate;
    /**
     * 用户id
     */
    private Integer memberId;
    /**
     * 发货订单状态
     */
    private String status;
    /**
     * '寄存有效期'
     */
    private Date stockValidDate;
    /**
     * '发货时间'
     */
    private Date deliverDate;
    /**
     * '发货方式（快递公司名称）'
     */
    private String deliverMethod;
    /**
     * '快递单号'
     */
    private String deliverNumber;
    /**
     * '邮费金额，暂不用，预留字段'
     */
    private BigDecimal deliverAmount;
    /**
     * '邮费等值的游戏币'
     */
    private Integer deliverCoins;
    private String dollitemids;
   /**
    * 发货娃娃信息
    */
    private String dollsInfo;
    /**
     * 收货用户名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 国家
     */
    private String county;
    /**
     * 街道
     */
    private String street;

    private Date createdDate;
    /**
     * 订单跟踪人员
     */
    private String modifiedBy;

    private Date modifiedDate;
    /***
     * 备注
     */
    private String comment;
    private String note;
}
