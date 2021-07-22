package com.mengyu.mock.api;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:49
 * TODO
 */
public class DemoServiceMock implements DemoService{
    @Override
    public String sayHello(String name) {
        return "容错数据";
    }
}
