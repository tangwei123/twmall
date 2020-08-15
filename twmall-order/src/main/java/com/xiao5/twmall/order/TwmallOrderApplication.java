package com.xiao5.twmall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.xiao5.twmall.order.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class TwmallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwmallOrderApplication.class, args);
    }
}
