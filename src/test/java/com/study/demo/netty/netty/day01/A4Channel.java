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

/**
 * @author caonan
 * @createtime 2023/7/6 10:06
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A4Channel {
    /**
     * close关闭channel
     * closeFuture 处理channel的关闭
     * pipeline 添加处理器
     * write 数据写入
     * writeAndFlush 写入数据并刷出
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup(2))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        log.info("某个channel的处理");
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect("127.0.0.1", 8080);
//        log.info(channelFuture.channel().toString());
//        log.info(channelFuture.sync().channel().toString());
        channelFuture.addListener((ChannelFutureListener) future -> {
            log.info(channelFuture.channel().toString());
        });

    }
}
