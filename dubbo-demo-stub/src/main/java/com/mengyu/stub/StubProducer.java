package com.mengyu.stub;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:30
 * TODO
 */
public class StubProducer {
    public static void main(String[] args) throws InterruptedException {
        new EmbeddedZooKeeper().start();

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/stub-producer.xml");
        ctx.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
