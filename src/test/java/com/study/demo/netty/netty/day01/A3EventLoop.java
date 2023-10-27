package com.study.demo.netty.netty.day01;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author caonan
 * @createtime 2023/7/6 9:56
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A3EventLoop {
    public static void main(String[] args) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(2);
        eventExecutors.execute(()->{
            log.info("任务正在执行");
        });

        eventExecutors.scheduleAtFixedRate(()->{
            log.info("定时任务正在执行");
        },0,1, TimeUnit.SECONDS);

        log.info("我才是主线程");
    }
}
