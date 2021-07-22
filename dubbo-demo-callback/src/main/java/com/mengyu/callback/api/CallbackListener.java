package com.mengyu.callback.api;

/**
 * @author yuzhang
 * @date 2021/5/23 下午3:49
 * TODO
 */
public interface CallbackListener {
    /**
     * 监听器列表发生变化后的回调
     * @param msg
     */
    void changed(String msg);
}
