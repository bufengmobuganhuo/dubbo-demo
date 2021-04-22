package org.mengyu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yuzhang
 * @date 2021/4/21 下午7:33
 * TODO
 */
public class MergeConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/merge-consumer.xml");
        ctx.start();

        MergeService mergeService = (MergeService) ctx.getBean("mergeService");
        System.out.println(mergeService.mergeResult());
    }
}
