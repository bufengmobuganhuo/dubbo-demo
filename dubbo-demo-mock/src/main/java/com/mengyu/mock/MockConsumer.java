package com.mengyu.mock;

import com.mengyu.mock.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:49
 * TODO
 */
public class MockConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/mock-consumer.xml");
        ctx.start();

        DemoService demoService = ctx.getBean(DemoService.class);
        System.out.println(demoService.sayHello("tom"));
    }
}
