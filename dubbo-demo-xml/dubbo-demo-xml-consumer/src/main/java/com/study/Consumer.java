package com.study;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yuzhang
 * @date 2021/4/13 下午2:12
 * TODO
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        DemoService demoService = applicationContext.getBean("demoService", DemoService.class);
        System.out.println(demoService.sayHello("tom"));
    }
}

