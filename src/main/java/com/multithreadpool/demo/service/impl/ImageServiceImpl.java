package com.multithreadpool.demo.service.impl;

import com.multithreadpool.demo.service.ImageService;
import com.multithreadpool.demo.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.Future;

@Service
public class ImageServiceImpl implements ImageService {

    private Logger logger= LoggerFactory.getLogger(MailServiceImpl.class);

    //演示，只是sleep1秒
    @Async(value="imageThreadPool")
    @Override
    public Future<String> asynctmb(int i) {
        logger.info("asynctmb begin");
        String start= TimeUtil.getMilliTimeNow();
        try {
            Thread.sleep(1000);    //延时1秒
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        //log.info("async function sleep   end");
        String end=TimeUtil.getMilliTimeNow();
        return new AsyncResult<>(String.format("asynctmb方法,第 %s 个线程:开始时间:%s,结束时间:%s",i,start,end));
    }
}
