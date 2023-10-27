package com.study.demo.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * @author caonan
 * @createtime 2023/6/30 10:26
 * @Description TODO
 * @Version 1.0
 */
public class A7NetworkNioServer {
    /**
     * selector
     * 说明：一个selector一个线程，对应多个channel
     *方法
     *      创建：Selector.open()
     *      注册：SelectionKey key = channel.register(selector, 绑定事件);
     *          绑定的事件必须是非阻塞模式
     *          filechannel不是非阻塞模式，所以不能和selector一起使用
     *          绑定的事件类型有：connect客户端连接，accept服务端成功接收连接，read数据可读，write数据可写
     *      监听事件是否发生
     *             selector.select(); 阻塞知道绑定事件发生
     *             selector.select(long timeout); 阻塞直到绑定事件发生或者是超时
     *             selector.selectNow();不阻塞，立刻返回，根据返回值确定有没有事件
     *       添加附件
     *             key.attachment()
     *      备注：select不阻塞的情况
 *          - 事件发生时
     *          - 客户端发起连接请求，会触发 accept 事件
     *          - 客户端发送数据过来，客户端正常、异常关闭时，都会触发 read 事件，另外如果发送的数据大于 buffer 缓冲区，会触发多次读取事件
     *          - channel 可写，会触发 write 事件
     *          - 在 linux 下 nio bug 发生时
 *          - 调用 selector.wakeup()
 *          - 调用 selector.close()
 *          - selector 所在线程 interrupt
     *     问题：消息边界处理
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Selector open = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));
        ssc.register(open, SelectionKey.OP_ACCEPT);
        while (true){
            int count = open.select();
            Set<SelectionKey> selectionKeys = open.selectedKeys();
            for (SelectionKey selectionKey:selectionKeys){
                if (selectionKey.isAcceptable()){//接入事件
                    ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
                    // 事件来了要不处理，要不取消cancel，什么都不做，该事件会持续触发
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(open,SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    SocketChannel socketChannel=(SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(2);
                    socketChannel.read(buffer);
                    buffer.flip();
                    System.out.println(Charset.defaultCharset().decode(buffer));
                    buffer.clear();
                }
                // 处理完毕必须将selectkey移除，没有移除的话第二次触发获取channel的时候实际上没有accept事件发生会发生空指针报错
                selectionKeys.remove(selectionKey);
            }
        }
    }
}
