package com.xiao5.twmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.xiao5.twmall.ware.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class TwmallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwmallWareApplication.class, args);
    }

}
