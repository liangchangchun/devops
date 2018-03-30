package com.zww.api.model;

import java.io.Serializable;
import java.util.Date;

/*
 * 签到类
 */
public class SignedSheet implements Serializable {

    private static final long serialVersionUID = 2893737172186611203L;
    private Integer signedId; //签到表id
    private String memberId; //用户id
    private String memberName; //用户名
    private Date signedDate; //签到日期
    private Date createTime; //创建日期
    private Integer signed; //连续签到天数
    private Integer signedCoin; //连续签到奖励娃娃币数

    public void setSignedCoin(Integer signedCoin) {
        this.signedCoin = signedCoin;
    }

    public Integer getSignedId() {
        return signedId;
    }

    public void setSignedId(Integer signedId) {
        this.signedId = signedId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSigned() {
        return signed;
    }

    public void setSigned(Integer signed) {
        this.signed = signed;
    }


    public Integer getSignedCoin() {
        switch (this.signed) {
            case 1:
                return 5;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 10;
            case 7:
                return 30;
            default:
                return 0;
        }
    }

    public String getChargeMethod() {
        return "连续签到 " + getSigned() + " 天,奖励游戏币数量 " + getSignedCoin();
    }
}
