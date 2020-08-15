package com.xiao5.twmall.product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class MyThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor createThreadPool(){
        return new ThreadPoolExecutor(20, 200, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100000));
    }
}
