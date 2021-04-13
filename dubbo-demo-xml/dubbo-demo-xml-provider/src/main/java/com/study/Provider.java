package com.study;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author yuzhang
 * @date 2021/4/13 下午2:08
 * TODO
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("provider.xml");
        applicationContext.start();
        // 让应用不要停，等读取了一个输入后再停止
        System.in.read();
    }
}
