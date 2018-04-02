
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

public class GameEventHandler     
{  
	protected SocketIOServer server;
	
	
    @Autowired  
    public GameEventHandler(SocketIOServer server)   
    {  
        this.server = server ;
    }  
    
    @OnConnect  
    public void onConnect(SocketIOClient client)  
    {  
    	System.out.println("发起连接");
    	RoomPlayer player = NettyClients.getInstance().getClient(client.getSessionId().toString());
    	if (player!=null  && !StringUtils.isBlank(player.getToken())) {
    		
    	}
    	String pathParam = "/{memberId}/{dollId}/{key}/{device}/{queue}/{token}";
    	String path = client.getHandshakeData().getSingleUrlParam("path") ;
    	System.out.println(path);
    	
    }  
    
  //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息  
    @OnDisconnect  
    public void onDisconnect(SocketIOClient client)  
    {  
    	
    }  
    
    
    //抢地主事件
    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息  
    @OnEvent(value = "message")    
    public void onJoinRoom(SocketIOClient client , AckRequest request,ChatObject chat)  
    {  
    	System.out.println(chat.getMessage());
    	client.sendEvent(BMDataContext.MessageTypeEnum.MESSAGE.toString(), chat);
    }
    
}  