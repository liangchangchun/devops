package com.stylefeng.game.rest.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.stylefeng.game.rest.config.BMDataContext;
import com.stylefeng.game.rest.server.handler.GameEventHandler;
import com.stylefeng.guns.game.service.MachineService;

  
@Component  
public class ServerRunner implements CommandLineRunner {  
	
    private final SocketIOServer server;
    private final SocketIONamespace gameSocketNameSpace ;
    @Autowired
    MachineService machineService;
    
    @Autowired  
    public ServerRunner(SocketIOServer server) {  
        this.server = server;  
        gameSocketNameSpace = server.addNamespace(BMDataContext.NameSpaceEnum.GAME.getNamespace());

    }
    
    @Bean(name="gameNamespace")
    public SocketIONamespace getGameSocketIONameSpace(SocketIOServer server ){
    	gameSocketNameSpace.addListeners( new GameEventHandler( server, machineService) );
    	return gameSocketNameSpace;
    }
    

    public void run(String... args) throws Exception { 
        server.start();  
        BMDataContext.setGameServerStatus(true);	//gameServer 启动成功
    }  
}  