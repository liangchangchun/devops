package com.stylefeng.game.rest.client;


public interface NettyClient {
	public RoomPlayer getClient(String key) ;
	
	public void putClient(String roomId , RoomPlayer room) ;
	
	public void removeClient(String id);
}
