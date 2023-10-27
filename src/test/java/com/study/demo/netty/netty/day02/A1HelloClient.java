package com.study.demo.netty.netty.day02;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author caonan
 * @createtime 2023/7/11 11:27
 * @Description 固定分隔符客户端
 * @Version 1.0
 */
@Slf4j
public class A1HelloClient {
    public static void main(String[] args) throws InterruptedException {
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        log.debug("connetted...");
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                log.debug("sending");
                                Random random = new Random();
                                for (int i = 0;i<10;i++){
                                    ByteBuf buffer = ctx.alloc().buffer();
                                    for (int j = 0;j<10-i;j++){
                                        buffer.writeBytes(String.valueOf(j).getBytes(StandardCharsets.UTF_8));
                                    }

                                    buffer.writeBytes("\n".getBytes(StandardCharsets.UTF_8));
                                    ctx.writeAndFlush(buffer);
                                }

                            }
                        });
                    }
                }).connect("127.0.0.1",8080).sync().channel().closeFuture().sync();
    }
}
