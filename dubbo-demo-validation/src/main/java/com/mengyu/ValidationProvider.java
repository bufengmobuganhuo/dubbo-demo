package com.mengyu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuzhang
 * @date 2021/4/22 上午11:06
 * TODO
 */
public class ValidationProvider {
    public static void main(String[] args) throws InterruptedException {
        new EmbeddedZookeeper(2181,false).stop();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/validation-provider.xml");
        ctx.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
