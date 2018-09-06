package com.zww.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootApplication
@EnableCaching
public class TestApplication //extends SpringBootServletInitializer 
{

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
	
	/* @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        // TODO Auto-generated method stub
	        // return super.configure(builder);
	        return builder.sources(TestApplication.class);
	    }
	    */
}
