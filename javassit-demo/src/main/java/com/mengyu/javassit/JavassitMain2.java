package com.mengyu.javassit;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author yuzhang
 * @date 2021/7/10 下午4:05
 * TODO
 */
public class JavassitMain2 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ProxyFactory factory = new ProxyFactory();
        // 指定父类，ProxyFactory会动态生成继承父类的子类
        factory.setSuperclass(JavassitDemo.class);
        // 设置过滤器，判断那些方法需要被拦截
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method method) {
                return "execute".equals(method.getName());
            }
        });
        // 设置拦截器
        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] objects) throws Throwable {
                System.out.println("前置处理");
                Object result = proceed.invoke(self, objects);
                System.out.println("执行结果："+result);
                System.out.println("后置处理");
                return result;
            }
        });
        // 创建JavassitDemo的代理类，并创建代理对象
        Class<?> c = factory.createClass();
        JavassitDemo javassitDemo = (JavassitDemo) c.newInstance();
        javassitDemo.execute();
        System.out.println(javassitDemo.getProp());
    }
}
