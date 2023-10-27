package com.study.demo.netty.netty.day01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author caonan
 * @createtime 2023/7/4 14:37
 * @Description TODO
 * @Version 1.0
 */
public class A1HelloWorld {
    public static void main(String[] args) {
        // 1.启动器 负责netty组件的组织
        new ServerBootstrap()
                // NioEventLoopGroup可以理解为线程池+selector
                .group(new NioEventLoopGroup()) //处理accept和read事件，处理read事件的时候获取到bytebuff传递给pipeline
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override       //客户端连接之后执行initchannel
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                         channel.pipeline().addLast(new StringDecoder()); // 5
                        channel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
            .bind(8080);
        ;
    }
}
