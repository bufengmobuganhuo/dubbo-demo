package com.mengyu.stub.api.impl;

import com.mengyu.stub.api.DemoService;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:30
 * TODO
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "hello, " + name;
    }
}
