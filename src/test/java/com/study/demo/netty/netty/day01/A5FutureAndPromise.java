package com.study.demo.netty.netty.day01;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author caonan
 * @createtime 2023/7/6 11:55
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A5FutureAndPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DefaultEventLoop eventLoop = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise(eventLoop);
        promise.addListener( future -> {
            log.info("{}",future.getNow());//getNow的值是空的
            log.info("result{}",(future.isSuccess()?future.getNow():future.cause()).toString());
        });
        eventLoop.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Throwable cause = new RuntimeException("错误场景");
            promise.setFailure(cause);
            /* 成功场景
            * promise.setSuccess(10);
            * */

        });
        log.info("{}",promise.getNow());
        promise.await();//不会报错
        log.info("awiat {}",promise.isSuccess()?promise.getNow().toString():promise.cause().toString());
//        log.info("{}",promise.get());//get和sync都会报错
    }
}
