package com.study.dubbodemoprovider;

import com.study.DemoService;

/**
 * @author yuzhang
 * @date 2021/4/13 下午3:14
 * TODO
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
