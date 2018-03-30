package com.stylefeng.game.rest.server.message;

import com.stylefeng.game.rest.util.UKTools;

public abstract class Message implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1872188129813937898L;
	private String id = UKTools.getUUID();
	private String appid ;
	private String userid ;
	private String username ;

	
	public abstract String getType() ;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}