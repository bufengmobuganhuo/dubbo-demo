package com.mengyu.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yuzhang
 * @date 2021/7/10 下午3:39
 * TODO
 */
public class CglibProxy<T> implements MethodInterceptor {
    // 初始化Enhancer对象
    private Enhancer enhancer = new Enhancer();

    public T getProxy(Class<T> clazz){
        // 制定生成的代理类的父类
        enhancer.setSuperclass(clazz);
        // 设置callback对象
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置处理");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后置处理");
        return result;
    }
}
