package com.mengyu;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author yu zhang
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
        for (Log log : serviceLoader) {
            log.log("JDK SPI " + log.getClass());
        }
    }
}
