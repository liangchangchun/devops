package com.stylefeng.game.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stylefeng.game.rest.config.property.AliyunProperties;
import com.stylefeng.guns.core.aliyun.AliyunModel;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.aliyun.impl.AliyunServiceImpl;

@Configuration
public class AliyunConfig extends AliyunModel{

	@Autowired 
	AliyunProperties aliyunProperties;

	@Bean(initMethod = "init")
    public AliyunService aliyunService() {
		AliyunServiceImpl aliyunService = new AliyunServiceImpl();
		aliyunService.setAliyunProperties(aliyunProperties);
		//aliyunService.init();
		return aliyunService;
    }
}
