package com.mengyu.stub;

import com.mengyu.stub.api.DemoService;
import com.mengyu.stub.api.DemoServiceStub;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:30
 * TODO
 */
public class StubConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/stub-consumer.xml");
        ctx.start();

        DemoService demoService = ctx.getBean(DemoService.class);
        demoService.sayHello("tom");
    }
}
