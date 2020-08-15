package com.xiao5.twmall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@MapperScan("com.xiao5.twmall.member.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TwmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwmallMemberApplication.class, args);
    }

}
