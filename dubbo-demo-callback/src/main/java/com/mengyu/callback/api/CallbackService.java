package com.mengyu.callback.api;

/**
 * @author yuzhang
 * @date 2021/5/23 下午3:51
 * 服务接口
 */
public interface CallbackService {
    void addListener(String key, CallbackListener listener);
}
