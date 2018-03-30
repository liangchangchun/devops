package com.zww.api.model;

import java.io.Serializable;

public class MemberInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Member member=new Member();
	private String token;
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "MemberInfo [member=" + member + ", token=" + token + "]";
	}
	
}
