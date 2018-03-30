package com.stylefeng.guns.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
@TableName("t_modify_record")
public class TModifyRecord extends Model<TModifyRecord> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 注册用户（玩家）
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id; //id
	private Integer memberId; //userid
	private Integer coins;//金币
	private Integer superTicket;//钻石
	private Date modifiedDate;//修改时间
	private Integer modifiedBy;//修改人



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoins() {
		return coins;
	}
	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getSuperTicket() {
		return superTicket;
	}

	public void setSuperTicket(Integer superTicket) {
		this.superTicket = superTicket;
	}


	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Member{" +
			"id=" + id +
			", coins=" + coins +
			", superTicket=" + superTicket +
			", memberId=" + memberId +
			", modifiedDate=" + modifiedDate +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
