package com.multithreadpool.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/*
使用多个线程池，
把相同的任务放到同一个线程池中，
可以起到隔离的作用，避免有出错时影响到其他线程池，
例如只有一个线程池时，
有两种任务，下单，处理图片，
线程池被处理图片占满，影响下单的进行
*/

//线程池的配置
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    //用来生成缩略图的线程池
    @Bean(name = "imageThreadPool")
    public ThreadPoolTaskExecutor imageThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数,它是可以同时被执行的线程数量
        executor.setCorePoolSize(2);
        // 设置最大线程数,缓冲队列满了之后会申请超过核心线程数的线程
        executor.setMaxPoolSize(10);
        // 设置缓冲队列容量,在执行任务之前用于保存任务
        executor.setQueueCapacity(50);
        // 设置线程生存时间（秒）,当超过了核心线程出之外的线程在生存时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("imagePool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //初始化
        executor.initialize();
        return executor;
    }

    //用来发邮件的线程池
    @Bean(name = "emailThreadPool")
    public ThreadPoolTaskExecutor emailThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数,它是可以同时被执行的线程数量
        executor.setCorePoolSize(2);
        // 设置最大线程数,缓冲队列满了之后会申请超过核心线程数的线程
        executor.setMaxPoolSize(10);
        // 设置缓冲队列容量,在执行任务之前用于保存任务
        executor.setQueueCapacity(50);
        // 设置线程生存时间（秒）,当超过了核心线程出之外的线程在生存时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("emailPool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //初始化
        executor.initialize();
        return executor;
    }
}
