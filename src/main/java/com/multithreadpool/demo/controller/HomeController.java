package com.multithreadpool.demo.controller;

import com.multithreadpool.demo.service.ImageService;
import com.multithreadpool.demo.service.MailService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RequestMapping("/home")
@Controller
public class HomeController {
    @Resource
    private MailService mailService;

    @Resource
    private ImageService imageService;

    @Resource
    private ThreadPoolTaskExecutor imageThreadPool;

    //监控线程池的状态,
    //我们得到的数字，只是大体接近，并不是严格的准确数字
    @GetMapping("/poolstatus")
    @ResponseBody
    public String poolstatus() {
        String statusStr = "";
        int queueSize = imageThreadPool.getThreadPoolExecutor().getQueue().size();
        statusStr +="当前排队线程数：" + queueSize;
        int activeCount = imageThreadPool.getThreadPoolExecutor().getActiveCount();
        statusStr +="当前活动线程数：" + activeCount;
        long completedTaskCount = imageThreadPool.getThreadPoolExecutor().getCompletedTaskCount();
        statusStr +="执行完成线程数：" + completedTaskCount;
        long taskCount = imageThreadPool.getThreadPoolExecutor().getTaskCount();
        statusStr +="总线程数：" + taskCount;
        return statusStr;
    }

    //异步发送一封注册成功的邮件
    @GetMapping("/asyncmail")
    @ResponseBody
    public String regMail() {
        mailService.sendHtmlMail();
        return "mail sended";
    }

    //异步执行sleep1秒10次
    @GetMapping("/asyncimage")
    @ResponseBody
    public Map<String, Object> asyncsleep() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Future<String> future = imageService.asynctmb(i);
            futures.add(future);
        }
        List<String> response = new ArrayList<>();
        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        map.put("data", response);
        map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
        return map;
    }
}
