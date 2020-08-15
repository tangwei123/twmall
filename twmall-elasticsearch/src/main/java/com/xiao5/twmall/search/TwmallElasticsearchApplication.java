package com.xiao5.twmall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TwmallElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwmallElasticsearchApplication.class, args);
    }
}
