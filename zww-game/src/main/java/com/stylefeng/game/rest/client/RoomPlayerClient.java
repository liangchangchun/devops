package com.stylefeng.game.rest.client;

import java.util.HashMap;
import java.util.Map;


public class RoomPlayerClient implements NettyClient {
	
	private Map<String, RoomPlayer> systemClientsMap = new HashMap<String,RoomPlayer>();

	@Override
	public RoomPlayer getClient(String key) {
		return  systemClientsMap.get(key);
	}

	@Override
	public void putClient(String roomId, RoomPlayer room) {
		systemClientsMap.put(roomId, room) ;
		systemClientsMap.put(room.getSession(), room) ;
	}

	@Override
	public void removeClient(String id) {
		RoomPlayer room = this.getClient(id) ;
		systemClientsMap.remove(id) ;
		if(room!=null){
			systemClientsMap.remove(String.valueOf(room.getRoomId())) ;
		}
	}
	
	public void sendGameEventMessage(String roomId, String event, Object data) {
		RoomPlayer roomClient = this.getClient(roomId) ;
		if(roomClient!=null){
			roomClient.getClient().sendEvent(event, data);
		}
	}

}
