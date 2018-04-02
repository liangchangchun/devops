package com.stylefeng.game.rest.client;

import com.corundumstudio.socketio.SocketIOClient;

public class RoomPlayer {
	//房间id
	private Integer roomId;
	private Integer userId;
	// 产品key
	private String key;
	private String device;
	private String queue;
	//房主token
	private String token;
	
	private String session ;
	private SocketIOClient client;
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public SocketIOClient getClient() {
		return client;
	}
	public void setClient(SocketIOClient client) {
		this.client = client;
	}
	
	
		
}
