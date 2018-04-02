package com.stylefeng.game.rest.server;

import java.io.IOException;


public   class KeepAliveWatchDog implements Runnable {  
    long checkDelay = 10;  
    long keepAliveDelay = 500;  
    String message;
    Integer dollId;
    public KeepAliveWatchDog(String message,Integer dollId){
    	this.message = message;
    	this.dollId = dollId;
    }
    public void run() { 
    	try {  
    		MachineUtils.sendObject(this.message,this.dollId);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    	/* while(running){  
        	
            if(System.currentTimeMillis()-lastSendTime>keepAliveDelay){  
                try {  
                	MachineServiceImpl.this.sendObject();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    MachineServiceImpl.this.stop();  
                }  
                lastSendTime = System.currentTimeMillis();  
            }else{  
                try {  
                    Thread.sleep(checkDelay);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                    MachineServiceImpl.this.stop();  
                }  
            }  
        }  */
    }  
}
