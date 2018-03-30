package com.stylefeng.game.rest.client;


public interface NettyClient {
	public Room getClient(String key) ;
	
	public void putClient(String roomId , Room room) ;
	
	public void removeClient(String id);
}
