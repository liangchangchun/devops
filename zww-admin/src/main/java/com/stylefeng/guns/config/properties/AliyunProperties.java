package com.stylefeng.guns.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.stylefeng.guns.core.aliyun.AliyunModel;

@Configuration
@ConfigurationProperties(prefix = AliyunProperties.PREFIX)
public class AliyunProperties extends AliyunModel {

    public static final String PREFIX = "aliyun";

	public static String getPrefix() {
		return PREFIX;
	}
    
}
