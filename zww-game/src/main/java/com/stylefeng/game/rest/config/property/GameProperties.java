package com.stylefeng.game.rest.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.stylefeng.guns.game.GameBaseProperties;


@Configuration
@ConfigurationProperties(prefix = GameProperties.PREFIX)
public class GameProperties extends GameBaseProperties {
    public static final String PREFIX = "webapi";

	public static String getPrefix() {
		return PREFIX;
	}
    
}
