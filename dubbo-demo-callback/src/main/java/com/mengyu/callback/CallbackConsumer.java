package com.mengyu.callback;

import com.mengyu.callback.api.CallbackService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yuzhang
 * @date 2021/5/23 下午3:58
 * TODO
 */
public class CallbackConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/callback-consumer.xml");
        ctx.start();

        CallbackService callbackService = ctx.getBean(CallbackService.class);
        callbackService.addListener("foo.bar", msg -> System.out.println("callback:"+msg));
    }

}
