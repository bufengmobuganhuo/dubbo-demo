package com.mengyu.mock.impl;

import com.mengyu.mock.api.DemoService;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:54
 * TODO
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        try {
            // 会触发TimeoutException
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name;
    }
}
