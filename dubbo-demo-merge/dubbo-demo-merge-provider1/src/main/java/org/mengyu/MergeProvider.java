package org.mengyu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuzhang
 * @date 2021/4/21 下午7:04
 * TODO
 */
public class MergeProvider {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/merge-provider.xml");
        ctx.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
