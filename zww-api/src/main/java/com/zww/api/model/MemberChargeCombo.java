package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 时长包充值套餐表
 * @author Administrator
 *
 */
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberChargeCombo implements Serializable {

    private static final long serialVersionUID = -8422287866656057489L;
    private Integer id;    //标识
    private Integer memberId; //用户id
    private String memberName;//用户名称
    private Integer chargeType;//充值类型 充值规则主键
    private String chargeName;        //充值名称
    private Integer chargeDateLimit;    //时长
    private Date chargeDateStart;    //套餐 起始时间
    private Integer coinsGive;        //每日赠送
    private Integer memberState;        //用户状态   0为初始效状态    赠送过程中   1为赠送完成状态 无效状态
    private Integer giveTimes;            //已赠送次数

}
