package com.study.demo.netty.netty.day01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author caonan
 * @createtime 2023/7/6 10:50
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A4CloseFutureChannel {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(2);
        Channel channel = new Bootstrap()
                .group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect("127.0.0.1", 8080)
                .sync()
                .channel();
//        new Thread(() -> {
//
//
//        }).start();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if ("q".equals(s)) {
                channel.close();
                break;
            }
            channel.writeAndFlush(s);
        }
        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.addListener((ChannelFutureListener) future -> {
            log.info("优雅的关闭处理");
            eventExecutors.shutdownGracefully();
        });
    }
}
