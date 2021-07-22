package com.mengyu.impl;

import com.mengyu.Log;

/**
 * @author yu zhang
 */
public class Logback implements Log {
    @Override
    public void log(String info) {
        System.out.println("Logback: " + info);
    }
}
