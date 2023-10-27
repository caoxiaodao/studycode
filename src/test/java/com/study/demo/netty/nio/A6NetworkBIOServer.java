package com.study.demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author caonan
 * @createtime 2023/6/29 15:28
 * @Description TODO
 * @Version 1.0
 */
public class A6NetworkBIOServer {
    public static void main(String[] args) throws IOException {
        /**
         * 网络变成阻塞方式
         *  说明：
         *      ServerSocketChannel.accept 接收不到新的连接就阻塞线程
         *      SocketChannel.read 接收不到新的写入就阻塞线程
         *      channel.configureBlocking(false); 设置非阻塞模式
         *  缺点：
         *      1.单线程模式下阻塞方法相互影响，几乎不能正常工作
         *      2.多线程模式下，随着连接建立的数量越来越多，可能会导致内存溢出；而且上下文切换也非常浪费时间
         *          32位jvm一个线程是320K，64JVM一个线程是1024K
         *      3.线程池技术处理，如果连接的数量超过一定的范围，还是会存在单线程存在的问题，某些请求需要等待无法被处理
         */
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));

        ArrayList<SocketChannel> channels = new ArrayList<>();
        while (true){
            SocketChannel channel = ssc.accept();
            channels.add(channel);
            for (SocketChannel sc:channels) {
                ByteBuffer buffer = ByteBuffer.allocate(10);
                sc.read(buffer);
                buffer.flip();
                System.out.println(Charset.defaultCharset().decode(buffer));
                buffer.clear();
            }
        }

    }
}
