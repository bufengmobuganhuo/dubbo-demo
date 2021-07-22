package com.mengyu.callback;

import com.mengyu.callback.api.CallbackService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuzhang
 * @date 2021/5/23 下午3:59
 * TODO
 */
public class CallbackProducer {
    public static void main(String[] args) throws InterruptedException {
        new EmbeddedZooKeeper(2181,false).start();

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/callback-producer.xml");
        ctx.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
