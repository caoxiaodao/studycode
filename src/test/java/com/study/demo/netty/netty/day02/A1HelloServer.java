package com.study.demo.netty.netty.day02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caonan
 * @createtime 2023/7/11 11:27
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A1HelloServer {
    /**
     * 正对于粘包半包问题解决方式
     * 1.短链接：效率太低
     * 2.固定长度：浪费空间
     * 3.固定分隔符：需要转义
     * 4.预设长度：每一条消息分为 head 和 body，head 中包含 body 的长度
     */
    public static void main(String[] args) throws InterruptedException {
        new ServerBootstrap().option(ChannelOption.SO_RCVBUF,10)
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new StringDecoder());
                        //2.固定长度：浪费空间
//                        ch.pipeline().addLast(new FixedLengthFrameDecoder(8));
//                        // 回车换行符：1024为最大长度
//                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024,0,1,0,1));
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                log.debug("connected {}", ctx.channel());
                                super.channelActive(ctx);
                            }

                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                log.debug("disconnect  {}", ctx.channel());
                                super.channelInactive(ctx);
                            }
                        });
                    }
                }).bind(8080).sync();


    }
}
