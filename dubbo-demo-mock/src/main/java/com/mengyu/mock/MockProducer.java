package com.mengyu.mock;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:50
 * TODO
 */
public class MockProducer {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/mock-producer.xml");
        ctx.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
