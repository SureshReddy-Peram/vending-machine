package com.example.vending_machine.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configurable
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "asyncOrderExecuter")
    public Executor asyncOrderExecuter(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("asyncOrderExecuterThread-");
        executor.initialize();
        return executor;
    }
}
