package com.xiao5.twmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TwmallGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwmallGatewayApplication.class, args);
	}

}
