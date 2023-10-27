package com.study.demo.netty.netty.day01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caonan
 * @createtime 2023/7/10 10:50
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A7Handler {
    /**
     *  入站1-->入站2-->入站3-->出站2-->出站1
     *  ctx.fireChannelRead(msg); 提交给下一个ChannelInboundHandlerAdapter
     *  ctx.channel.write(msg);从尾部的ChannelOutboundHandlerAdapter出站
     *  ctx.write(msg);从当前处理器往前出战ChannelOutboundHandlerAdapter出站
     * @param args
     */
    public static void main(String[] args) {
        ChannelFuture channelFuture = new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("第1个inbound");
                                ctx.fireChannelRead(msg); // 1
                            }
                        });
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("第2个inbound");
                                ctx.fireChannelRead(msg); // 2
//                                ctx.channel().write(msg);
                            }
                        });
                        ch.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg,
                                              ChannelPromise promise) {
                                System.out.println("第1个outbound");
                                ctx.write(msg, promise); // 4
                            }
                        });
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("第3个inbound");
//                                ctx.fireChannelRead(msg);
//                                ctx.channel().write(msg); // 2
                                ctx.write(msg);
                            }
                        });

                        ch.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg,
                                              ChannelPromise promise) {
                                System.out.println("第2个outbound");
                                ctx.write(msg, promise); // 4
                            }
                        });
                    }
                }).bind(8080);
    }
}
