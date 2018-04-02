package com.stylefeng.game.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.stylefeng.game.rest.config.property.GameProperties;
import com.stylefeng.guns.core.redis.RedisService;
import com.stylefeng.guns.core.redis.impl.RedisServiceImpl;
import com.stylefeng.guns.game.service.MachineService;
import com.stylefeng.guns.game.service.impl.MachineServiceImpl;

@Configuration
public class MachineServiceConfig {
	
	@Autowired
	GameProperties gameProperties;
	
	@Autowired
	RedisTemplate redisTemplate;
	//@Autowired
	//RedisService redisService;
	
	@Bean(initMethod = "init")
    public MachineService machineService() {
		MachineServiceImpl machineService = new MachineServiceImpl();
		machineService.setGameProperties(gameProperties);
		RedisServiceImpl redisService = new RedisServiceImpl();
    	redisService.setRedisTemplate(redisTemplate);
		machineService.setRedisUtil(redisService);
		//machineService.init();//初始化  游戏核心
		return machineService;
    }
}
