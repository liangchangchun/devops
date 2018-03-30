package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charge implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer memberId;
    private Integer inviteMemberId;//邀请人
    private Double prepaidAmt; //充值金额
    private Integer coins; //币数
    private Integer coinsSum;//总币数
    private Integer superTicket; //强爪券数
    private Integer superTicketSum;//总强爪券数
    private Integer inviteCoinsSum;//邀请人总币数
    private Timestamp chargeDate;
    private String chargeMethod;//充值方式
    private Double chargePrice;//规则金额
    private Integer coinsCharge;//规则币数
    private Integer coinsOffer; //规则赠送币数
    private String description;//描述
    private Double discount;//折扣
    private Integer redeemCoins;
    private Integer dollId;
    private String code; //系统设置code
    /*
     * income: 收入
     * expense: 支出
     */
    private String type;
}