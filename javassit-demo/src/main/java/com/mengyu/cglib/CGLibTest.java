package com.mengyu.cglib;

/**
 * @author yuzhang
 * @date 2021/7/10 下午3:44
 * 目标类
 */
public class CGLibTest {
    // 目标方法
    public String method(String str){
        System.out.println(str);
        return "CGLibTest.method(): " + str;
    }

    public static void main(String[] args) {
        CglibProxy<CGLibTest> cglibProxy = new CglibProxy<>();
        CGLibTest proxyImp = cglibProxy.getProxy(CGLibTest.class);
        // 调用生成的代理对象的method()方法
        String result = proxyImp.method("test");
        System.out.println(result);
    }
}
