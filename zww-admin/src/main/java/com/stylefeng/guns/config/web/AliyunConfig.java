package com.stylefeng.guns.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stylefeng.guns.config.properties.AliyunProperties;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.aliyun.impl.AliyunServiceImpl;

@Configuration
public class AliyunConfig  {
	
	@Autowired 
	AliyunProperties aliyunProperties;

	@Bean(initMethod = "init")
    public AliyunService aliyunService() {
		AliyunServiceImpl aliyunService = new AliyunServiceImpl();
		aliyunService.setAliyunProperties(aliyunProperties);
		aliyunService.init();
		return aliyunService;
    }
    

}
