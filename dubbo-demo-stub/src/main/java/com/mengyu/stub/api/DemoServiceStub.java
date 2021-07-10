package com.mengyu.stub.api;

/**
 * @author yuzhang
 * @date 2021/5/23 下午4:27
 * 对服务的实现的一个代理
 */
public class DemoServiceStub implements DemoService {

    private final DemoService demoService;

    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        try{
            // 真正调用服务的地方
            String result = demoService.sayHello(name);
            System.out.println("after execute remote service, result: "+result);
            return "stub - "+result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
