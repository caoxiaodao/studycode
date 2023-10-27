package com.study.demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/**
 * @author caonan
 * @createtime 2023/7/4 10:03
 * @Description TODO
 * @Version 1.0
 */
public class A6NetworkSocketUdp {
    /**
     * DatagramChannel 是udp连接
     *   udp是无连接传输数据，包括包头（占用8字节）和有效载荷（底层网络的最大传输单元，以太网中mtu是1500字节，有效载荷是1492字节）
     * socketChannel 是tcp连接
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        datagramChannel.write(Charset.defaultCharset().encode("hello"));
        System.out.println("udp数据已经发送");
    }
}
