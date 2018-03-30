package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 礼品码类
 * Created by SUN on 2018/1/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedeemCode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 137008822752599445L;
	private int id;
    //礼品码
    private String cdkey;
    //赠送金币数
    private int coins;
    //赠送钻石数
    private int superTicket;
    //赠送周卡天数
    private int weeksCardTime;
    //赠送月卡天数
    private int monthCardTime;
    //礼品码过期时间
    private Date validStartDate;
    //礼品码兑换状态
    private boolean state;
    //礼品码名称
    private String name;
    //兑奖人
    private Integer givinger;
    //赠送娃娃编码
    private String dollCode;

}
