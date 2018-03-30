package com.stylefeng.game.rest.client;

import java.util.HashMap;
import java.util.Map;


public class RoomClient implements NettyClient {
	
	private Map<String, Room> systemClientsMap = new HashMap<String,Room>();

	@Override
	public Room getClient(String key) {
		return  systemClientsMap.get(key);
	}

	@Override
	public void putClient(String roomId, Room room) {
		systemClientsMap.put(roomId, room) ;
		systemClientsMap.put(room.getSession(), room) ;
	}

	@Override
	public void removeClient(String id) {
		Room room = this.getClient(id) ;
		systemClientsMap.remove(id) ;
		if(room!=null){
			systemClientsMap.remove(String.valueOf(room.getRoomId())) ;
		}
	}
	
	public void sendGameEventMessage(String roomId, String event, Object data) {
		Room roomClient = this.getClient(roomId) ;
		if(roomClient!=null){
			roomClient.getClient().sendEvent(event, data);
		}
	}

}
