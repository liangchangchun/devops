package com.stylefeng.game.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.stylefeng.game.rest.config.BMDataContext;

@EnableAutoConfiguration
@SpringBootApplication
@EnableWebSecurity //启用web安全  
public class GameApplication {

    public static void main(String[] args) {
    	SpringApplication springApplication = new SpringApplication(GameApplication.class);
        BMDataContext.setApplicationContext(springApplication.run(args));
    }
}
