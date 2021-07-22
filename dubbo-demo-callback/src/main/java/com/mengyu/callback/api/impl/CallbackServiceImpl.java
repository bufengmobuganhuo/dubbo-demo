package com.mengyu.callback.api.impl;

import com.mengyu.callback.api.CallbackListener;
import com.mengyu.callback.api.CallbackService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuzhang
 * @date 2021/5/23 下午3:52
 * TODO
 */
public class CallbackServiceImpl implements CallbackService {
    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<>();

    public CallbackServiceImpl(){

    }

    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key,listener);
        // 添加监听器时，监听器列表也发生了变化
        listener.changed(getChangedStr());
    }

    private String getChangedStr(){
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
