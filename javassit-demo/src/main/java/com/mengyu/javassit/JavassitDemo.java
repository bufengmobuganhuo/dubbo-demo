package com.mengyu.javassit;

public class JavassitDemo {
    private String prop = "MyName";

    public String getProp() {
        return this.prop;
    }

    public void setProp(String var1) {
        this.prop = var1;
    }

    public JavassitDemo() {
        this.prop = "MyName";
    }

    public void execute() {
        System.out.println("execute():" + this.prop);
    }
}

