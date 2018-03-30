package com.zww.api.model;

import java.io.Serializable;

public class PrefSet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3994142915521092292L;
	/**
	 * 
	 */
	private Integer memberId;
	private Integer musicFlg;
	private Integer soundFlg;
	private Integer shockFlg;
	
	
	public Integer getShockFlg() {
		return shockFlg;
	}
	public void setShockFlg(Integer shockFlg) {
		this.shockFlg = shockFlg;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getMusicFlg() {
		return musicFlg;
	}
	public void setMusicFlg(Integer musicFlg) {
		this.musicFlg = musicFlg;
	}
	public Integer getSoundFlg() {
		return soundFlg;
	}
	public void setSoundFlg(Integer soundFlg) {
		this.soundFlg = soundFlg;
	}
	@Override
	public String toString() {
		return "PrefSet [memberId=" + memberId + ", musicFlg=" + musicFlg + ", soundFlg=" + soundFlg + ", shockFlg="
				+ shockFlg + "]";
	}
	
}
