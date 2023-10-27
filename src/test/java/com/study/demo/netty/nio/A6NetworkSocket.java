package com.study.demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author caonan
 * @createtime 2023/6/29 15:49
 * @Description TODO
 * @Version 1.0
 */
public class A6NetworkSocket {
    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        open.connect(new InetSocketAddress("127.0.0.1",8080));
        open.write(Charset.defaultCharset().encode("你好"));
        System.out.println("已经在连接服务器了");
    }
}
