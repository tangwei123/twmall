package com.xiao5.twmall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.xiao5.twmall.coupon.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class TwmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwmallCouponApplication.class, args);
    }

}
