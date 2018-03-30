package com.zww.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication(scanBasePackages={"com.zww.api.hystrix","com.zww.pay"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ("com.zww.api.service"))
@EnableEurekaClient
public class PayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayServiceApplication.class, args);
	}

}
