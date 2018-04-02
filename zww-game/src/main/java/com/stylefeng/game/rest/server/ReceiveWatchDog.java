package com.stylefeng.game.rest.server;

import java.io.InputStream;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.game.rest.client.NettyClients;
import com.stylefeng.game.rest.client.RoomPlayer;
import com.stylefeng.game.rest.config.BMDataContext;
import com.stylefeng.game.rest.server.message.ChatObject;
import com.stylefeng.guns.core.redis.RedisService;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.game.service.impl.MachineServiceImpl;


public class ReceiveWatchDog implements Runnable{  
	private Integer dollId;
	private Integer userId;
	private volatile Boolean running = true;
	@Autowired
	RedisService redisUtil;
	@Autowired
	MachineServiceImpl machineServiceImpl;
	
	public ReceiveWatchDog(Integer dollId,Integer userId){
		this.dollId = dollId;
		this.userId = userId;
	}
    public void run() {  
    	this.running = MachineUtils.socketRunning.get(this.dollId);
        while(this.running){  
            try {  
            	Socket  socket= MachineUtils.machineSocketMap.get(this.dollId);
            	if(socket==null || socket.isClosed()) {return;}
                InputStream in = socket.getInputStream();  
                if(in.available()>0){  
 					/*try {
 							BufferedReader br = new BufferedReader(new InputStreamReader(in));
 							String info = null;
 							while((info=br.readLine())!=null){
 								System.out.println("我是客户端，服务器说："+info);
 								WebSocketController.sendMessage(info,dollId);
 							}
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 						return;
 					}*/
                	byte[] bytes = new byte[1024];
                	while(true){
    					//读取数据（阻塞）
    					int read = in.read(bytes);
    					if(read != -1){
    						String info =new String(bytes, 0, read);
    						//System.out.println("++++++++++++"+info+"新机器接受指令"+RedisKeyGenerator.getUserGameCatch(userId));
    						//if(info.indexOf("gotToy")>0) {
    							//redisUtil.setString(RedisKeyGenerator.getUserGameCatch(userId), "1", 60*2);
    			           // }
    						if(machineServiceImpl.onReceived(info,this.userId,dollId)) {
    							//收到ready产生游戏编号
    							//WebSocketController webSocket = WebSocketController.webSocketControllerMap.get(dollId);
    							RoomPlayer player =  NettyClients.getInstance().getClient(String.valueOf(dollId));
    							//webSocket.sendMessage(info, dollId, webSocket.popMsgFlag);
    							ChatObject data = new ChatObject(userId,info);
    							player.getClient().sendEvent(BMDataContext.MessageTypeEnum.MESSAGE.toString(),data);
    						};
    					}else{
    						break;
    					}
    				}
                    /*ObjectInputStream ois = new ObjectInputStream(in);  
                    Object obj = ois.readObject();  
                    System.out.println("接收：\t"+obj);  
                    ObjectAction oa = actionMapping.get(obj.getClass());  
                    oa = oa==null?new DefaultObjectAction():oa;  
                    oa.doAction(obj, MachineServiceImpl.this);  */
                } else {  
                   Thread.sleep(1000);
                }  
            } catch (Exception e) {  
                //e.printStackTrace();  
                this.running = false;
            }   
            
        }  
    }
	public Boolean getRunning() {
		return running;
	}
	public void setRunning(Boolean running) {
		this.running = running;
	}  
    
    
}  
