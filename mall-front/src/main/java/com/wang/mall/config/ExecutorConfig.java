package com.wang.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 王念
 * @create 2020-02-09 23:21
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    //核心线程数
    @Value("${threadPool.core-pool-size}")
    private int corePoolSize;
    //最大线程数
    @Value("${threadPool.max-pool-size}")
    private int maxPoolSize;
    //队列大小
    @Value("${threadPool.queue-size}")
    private int queueSize;
    //线程前缀
    @Value("${threadPool.thread-name-prefix}")
    private String threadNamePrefix;

    @Bean
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueSize);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
