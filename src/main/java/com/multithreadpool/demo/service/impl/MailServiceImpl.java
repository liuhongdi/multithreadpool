package com.multithreadpool.demo.service.impl;

import com.multithreadpool.demo.service.MailService;
import com.multithreadpool.demo.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailServiceImpl  implements MailService {

    private Logger logger= LoggerFactory.getLogger(MailServiceImpl.class);

    @Resource
    private MailUtil mailUtil;

    //异步发送html格式的邮件，演示时只是sleep1秒
    @Async(value="emailThreadPool")
    @Override
    public void sendHtmlMail() {
         logger.info("sendHtmlMail begin");
        try {
            Thread.sleep(2000);    //延时1秒
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        /*
        String from = "demouser@163.com";
        String[] to = {"371125307@qq.com"};
        String subject = "恭喜您成功注册老刘代码库网站";
        //String content = "邮件已发送成功";
        HashMap<String,String> content= new HashMap<String,String>();
        content.put("username","laoliu");
        content.put("nickname","老刘");
        content.put("id","0000001");
        String templateName= "mail/regmail.html";
        try {
            mailUtil.sendHtmlMail(from, to, null, null, subject, templateName, content);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送出错");
        }
         */
    }
}
