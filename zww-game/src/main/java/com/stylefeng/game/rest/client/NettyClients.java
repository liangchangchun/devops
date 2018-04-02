package com.stylefeng.game.rest.client;



public class NettyClients {
	
	
	private static RoomPlayerClient systemClients = new RoomPlayerClient();
	
	public static RoomPlayerClient getInstance(){
		return systemClients ;
	}
	
	public void putGameEventClient(String id , RoomPlayer palyer){
		systemClients.putClient(id, palyer);
	}
	public void removeGameEventClient(String id){
		systemClients.removeClient(id);
	}
	public void sendGameEventMessage(String id , String event , Object data){
		systemClients.getClient(id).getClient().sendEvent(event, data);
	}
	
	public void joinRoom(String id , String roomid){
		systemClients.getClient(id).getClient().joinRoom(roomid);
	}
}
