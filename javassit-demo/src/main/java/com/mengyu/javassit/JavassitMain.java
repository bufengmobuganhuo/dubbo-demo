package com.mengyu.javassit;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author yuzhang
 * @date 2021/7/10 下午3:49
 * TODO
 */
public class JavassitMain {
    public static void main(String[] args) throws Exception{
        // 创建ClassPool
        ClassPool cp = ClassPool.getDefault();
        // 要生成的类名称为com.mengyu.JavassitDemo
        CtClass clazz = cp.makeClass("com.mengyu.javassit.JavassitDemo");

        StringBuilder body = null;
        // 创建字段，制定字段类型，字段名称，字段所属的类
        CtField field = new CtField(cp.get("java.lang.String"), "prop", clazz);
        // 指定该字段使用private修饰
        field.setModifiers(Modifier.PRIVATE);

        // 设置prop字段的getter/setter方法
        clazz.addMethod(CtNewMethod.getter("getProp", field));
        clazz.addMethod(CtNewMethod.setter("setProp", field));
        // 设置prop字段的初始化值，并将prop字段添加到clazz中
        clazz.addField(field, CtField.Initializer.constant("MyName"));

        // 创建构造方法，指定了方法的参数类型和构造方法所属的类
        CtConstructor constructor = new CtConstructor(new CtClass[]{}, clazz);
        // 设置方法体
        body = new StringBuilder();
        body.append("{\n prop=\"MyName\";\n}");
        constructor.setBody(body.toString());
        clazz.addConstructor(constructor);

        // 创建execute()方法，指定了方法返回值，方法名称，方法参数列表以及方法所属的类
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[]{}, clazz);
        // 指定该方法使用public修改好死
        ctMethod.setModifiers(Modifier.PUBLIC);
        body = new StringBuilder();
        body.append("{\n System.out.println(\"execute():\" " + " + this.prop);");
        body.append("\n}");
        ctMethod.setBody(body.toString());
        clazz.addMethod(ctMethod);
        // 将上面定义的JavassitDemo类保存到指定目录
        clazz.writeFile("/Volumes/F/dubbo-demo/javassit-demo/src/main/java");
        // 加载clazz类，创建对象
        Class<?> c = clazz.toClass();
        Object o = c.newInstance();
        // 调用execute方法
        Method method = o.getClass().getMethod("execute", new Class[]{});
        method.invoke(o, new Object[] {});

    }
}
