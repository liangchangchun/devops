package com.zww.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stylefeng.guns.core.aliyun.AliyunModel;
import com.stylefeng.guns.core.aliyun.AliyunService;
import com.stylefeng.guns.core.aliyun.impl.AliyunServiceImpl;
import com.zww.account.config.properties.AliyunProperties;

@Configuration
public class AliyunConfig extends AliyunModel{

	@Autowired 
	AliyunProperties aliyunProperties;

	@Bean(initMethod = "init")
    public AliyunService aliyunService() {
		AliyunServiceImpl aliyunService = new AliyunServiceImpl();
		aliyunService.setAliyunProperties(aliyunProperties);
		return aliyunService;
    }
}
