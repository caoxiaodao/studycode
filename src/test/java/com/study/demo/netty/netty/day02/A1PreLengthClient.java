package com.study.demo.netty.netty.day02;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author caonan
 * @createtime 2023/9/14 16:09
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A1PreLengthClient {
    public static void main(String[] args) throws InterruptedException {
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                log.debug("sending...");
                                Random r = new Random();
                                ByteBuf buffer = ctx.alloc().buffer();
                                for (int i = 0; i < 10; i++) {
                                    byte length = (byte) (r.nextInt(16) + 1);
                                    // 先写入长度
                                    buffer.writeByte(length);
                                    log.info(String.valueOf((int) length));
                                    // 再
                                    for (int j = 1; j <= length; j++) {
                                        buffer.writeBytes(String.valueOf(j).getBytes(StandardCharsets.UTF_8));
                                    }
                                }
                                ctx.writeAndFlush(buffer);
                            }
                        });
                    }
                }).connect("127.0.0.1",8080)
                .sync()
                .channel().closeFuture().sync();
    }
}
