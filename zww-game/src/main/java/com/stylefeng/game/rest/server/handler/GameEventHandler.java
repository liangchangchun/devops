
package com.stylefeng.game.rest.server.handler;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.stylefeng.game.rest.client.NettyClients;
import com.stylefeng.game.rest.client.RoomPlayer;
import com.stylefeng.game.rest.client.RoomPlayerClient;
import com.stylefeng.game.rest.config.BMDataContext;
import com.stylefeng.game.rest.server.message.ChatObject;
import com.stylefeng.guns.game.service.MachineService;

public class GameEventHandler     
{  
	protected SocketIOServer server;
	
	protected MachineService machineService;
	
    @Autowired  
    public GameEventHandler(SocketIOServer server,MachineService machineService)   
    {  
        this.server = server ;
        this.machineService = machineService;
    }  
    
    private ClientParam parseParam(String path) {
    	ClientParam param = new ClientParam() ;
    	String[] ams = path.split("/") ;
    	param.setUserId(ams[1]);
    	param.setDollId(ams[2]);
    	param.setKey(ams[3]);
    	param.setDevice(ams[4]);
    	param.setQueue(ams[5]);
    	param.setToken(ams[6]);
    	return param ;
    }
    
    @OnConnect  
    public void onConnect(SocketIOClient client)  
    {  
    	System.out.println("发起连接");
    	RoomPlayer player = NettyClients.getInstance().getClient(client.getSessionId().toString());
    	if (player!=null && !StringUtils.isBlank(player.getToken())) {
    		
    	} else {
    		//String pathParam = "/{memberId}/{dollId}/{key}/{device}/{queue}/{token}";
    		String path = client.getHandshakeData().getSingleUrlParam("path") ;
    		System.out.println(path);
    		ClientParam param = parseParam(path);
    		String userId = param.getUserId();
    		Integer dollId = Integer.parseInt(param.getDollId());
    		if (machineService.standMachine(userId, dollId)) {
    			//有人已经上机
    		}
    		
    	}
    }  
    
  //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息  
    @OnDisconnect  
    public void onDisconnect(SocketIOClient client)  
    {  
    	
    }  
    //上级 指令
    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息  
    @OnEvent(value = "instruct")    
    public void onInstruct(SocketIOClient client , AckRequest request,ChatObject chat)  
    {  
    	System.out.println(chat.getMessage());
    	client.sendEvent(BMDataContext.MessageTypeEnum.INSTRUCT.toString(), chat);
    }
    //
    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息   发消息
    @OnEvent(value = "message")    
    public void onMessage(SocketIOClient client , AckRequest request,ChatObject chat)  
    {  
    	System.out.println(chat.getMessage());
    	client.sendEvent(BMDataContext.MessageTypeEnum.MESSAGE.toString(), chat);
    }
    
 
}  