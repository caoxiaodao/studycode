package com.study.demo.netty.netty.day01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caonan
 * @createtime 2023/7/5 18:07
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A2EventLoop {
    public static void main(String[] args) {
        DefaultEventLoopGroup eventExecutors = new DefaultEventLoopGroup(2);
        new ServerBootstrap()
                //accept和read事件分别由两个不通线程池进行处理
                .group(new NioEventLoopGroup(1),new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(eventExecutors, new SimpleChannelInboundHandler() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                log.info(byteBuf.toString());
                            }
                        });
                    }
                });
    }
}
