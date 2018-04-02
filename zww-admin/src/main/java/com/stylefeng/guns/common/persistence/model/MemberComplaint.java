package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
@TableName("member_complaint")
public class MemberComplaint extends Model<MemberComplaint> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 游戏编号
     */
	@TableField("game_num")
	private String gameNum;
    /**
     * 用户id
     */
	@TableField("member_id")
	private Integer memberId;
    /**
     * 用户编号
     */
	@TableField("member_num")
	private String memberNum;
    /**
     * 娃娃机id
     */
	@TableField("doll_id")
	private Integer dollId;
    /**
     * 抓取记录id
     */
	@TableField("member_catch_id")
	private Integer memberCatchId;
    /**
     * 申诉原因
     */
	@TableField("complaint_reason")
	private String complaintReason;
    /**
     * 审核状态（待审核0，通过返币1，通过发娃娃2，未通过-1）
     */
	@TableField("check_state")
	private Integer checkState;
    /**
     * 审核理由
     */
	@TableField("check_reason")
	private String checkReason;
    /**
     * 创建日期
     */
	@TableField("creat_date")
	private Date creatDate;
    /**
     * 创建人
     */
	@TableField("creat_by")
	private Integer creatBy;
    /**
     * 修改时间
     */
	@TableField("modify_date")
	private Date modifyDate;
    /**
     * 修改人
     */
	@TableField("modify_by")
	private Integer modifyBy;

	@TableField(exist=false)
	private String memberName;//用户昵称

	@TableField(exist=false)
	private String memberPhone;//用户手机

	@TableField(exist=false)
	private String dollImg;//娃娃机头像

	@TableField(exist=false)
	private String dollName;//娃娃机名称

	@TableField(exist=false)
	private Integer dollCions;//消耗金币数

	@TableField(exist=false)
	private Date memberCatchDate;//抓取时间

	@TableField(exist=false)
	private String memberCatchResult;//抓取结果

	@TableField(exist=false)
	private String videoURL;//抓取视频

	@TableField(exist=false)
	private String complaintChannel;//申诉渠道

	@TableField(exist=false)
	private String person; //操作人

	@TableField(exist=false)
	private String vipGroup; //vip等级


	public String getVipGroup() {
		return vipGroup;
	}

	public void setVipGroup(String vipGroup) {
		this.vipGroup = vipGroup;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getComplaintChannel() {
		return complaintChannel;
	}

	public void setComplaintChannel(String complaintChannel) {
		this.complaintChannel = complaintChannel;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getDollImg() {
		return dollImg;
	}

	public void setDollImg(String dollImg) {
		this.dollImg = dollImg;
	}

	public String getDollName() {
		return dollName;
	}

	public void setDollName(String dollName) {
		this.dollName = dollName;
	}

	public Integer getDollCions() {
		return dollCions;
	}

	public void setDollCions(Integer dollCions) {
		this.dollCions = dollCions;
	}

	public Date getMemberCatchDate() {
		return memberCatchDate;
	}

	public void setMemberCatchDate(Date memberCatchDate) {
		this.memberCatchDate = memberCatchDate;
	}

	public String getMemberCatchResult() {
		return memberCatchResult;
	}

	public void setMemberCatchResult(String memberCatchResult) {
		this.memberCatchResult = memberCatchResult;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameNum() {
		return gameNum;
	}

	public void setGameNum(String gameNum) {
		this.gameNum = gameNum;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public Integer getDollId() {
		return dollId;
	}

	public void setDollId(Integer dollId) {
		this.dollId = dollId;
	}

	public Integer getMemberCatchId() {
		return memberCatchId;
	}

	public void setMemberCatchId(Integer memberCatchId) {
		this.memberCatchId = memberCatchId;
	}

	public String getComplaintReason() {
		return complaintReason;
	}

	public void setComplaintReason(String complaintReason) {
		this.complaintReason = complaintReason;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public Integer getCreatBy() {
		return creatBy;
	}

	public void setCreatBy(Integer creatBy) {
		this.creatBy = creatBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberComplaint{" +
			"id=" + id +
			", gameNum=" + gameNum +
			", memberId=" + memberId +
			", memberNum=" + memberNum +
			", dollId=" + dollId +
			", memberCatchId=" + memberCatchId +
			", complaintReason=" + complaintReason +
			", checkState=" + checkState +
			", checkReason=" + checkReason +
			", creatDate=" + creatDate +
			", creatBy=" + creatBy +
			", modifyDate=" + modifyDate +
			", modifyBy=" + modifyBy +
			", memberName=" + memberName +
			", memberPhone=" + memberPhone +
			", dollImg=" + dollImg +
			", dollName=" + dollName +
			", dollCions=" + dollCions +
			", memberCatchDate=" + memberCatchDate +
			", memberCatchResult=" + memberCatchResult +
			", videoURL=" + videoURL +
			", complaintChannel=" + complaintChannel +
			", person=" + person +
			", vipGroup=" + vipGroup +
			"}";
	}
}
