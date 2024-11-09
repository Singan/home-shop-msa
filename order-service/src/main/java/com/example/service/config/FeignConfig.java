package com.example.service.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        // 첫 번째 재시도는 1초 뒤, 이후 재시도는 최대 20초까지 대기하고 최대 5번까지 재시도
        return new Retryer.Default(1000, 20000, 5);
    }
}