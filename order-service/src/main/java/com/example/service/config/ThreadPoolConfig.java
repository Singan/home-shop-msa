package com.example.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {
    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(500);
        threadPoolExecutor.setKeepAliveSeconds(60);
        threadPoolExecutor.setThreadNamePrefix("my-executor-");
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }

}
