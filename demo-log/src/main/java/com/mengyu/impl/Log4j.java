package com.mengyu.impl;

import com.mengyu.Log;

/**
 * @author yu zhang
 */
public class Log4j implements Log {

    @Override
    public void log(String info) {
        System.out.println("Log4j: " + info);
    }
}
