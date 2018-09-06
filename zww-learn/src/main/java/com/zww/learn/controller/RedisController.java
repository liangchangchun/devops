package com.zww.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zww.learn.config.RedisClient;

@RestController 
public class RedisController {
	
	@Autowired  
     private RedisClient redisClinet;

	 @RequestMapping("/set")  
	 public String set(String key, String value) throws Exception{  
		 for (long i=0;i<50000;i++) {
			 redisClinet.set(key+i, value+i);  
		 }
	        return "success";  
	 }  
	      
	  @RequestMapping("/get")  
	  public String get(String key) throws Exception {  
	    return redisClinet.get(key);  
	  }  


}
