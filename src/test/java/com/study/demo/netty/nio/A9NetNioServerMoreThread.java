package com.study.demo.netty.nio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caonan
 * @createtime 2023/7/3 10:18
 * @Description 多线程版nio
 * @Version 1.0
 */
@Slf4j
public class A9NetNioServerMoreThread {
    /**
     * 问题：
     * 单线程处理存在问题，如果某个处理非常耗时会影响别的请求处理
     * 创建
     *  BOSS thread和selector 只关注accept事件
     *  worker thread和selector 关注读写操作
     * @param args
     */
    public static void main(String[] args) {
        Thread.currentThread().setName("boss");
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            Selector boss = Selector.open();
            ssc.configureBlocking(false);
            ssc.register(boss, SelectionKey.OP_ACCEPT);
            ssc.bind(new InetSocketAddress(8080));
            // 获取硬件cpu核数是对的，但是docker的cpu核数的话是错误的，bug在java10才解决
            Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
            for (int i=0;i<workers.length;i++){
                Worker worker = new Worker("worker-"+i);
                workers[i] = worker;
            }
            AtomicInteger atomicInteger = new AtomicInteger();
            while (true){
                boss.select();
                Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()){
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        workers[(atomicInteger.getAndIncrement()%workers.length)].init(socketChannel);
//                        // 关联selector
//                        worker.init(socketChannel);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static class Worker implements Runnable{
        private Selector worker;
        private String name;
        private Thread thread;
        private boolean flag = false;

        public Worker(String name) {
            this.name = name;
        }
        public void init(SocketChannel socketChannel) throws IOException {
            if (!flag){
                worker = Selector.open();
                thread = new Thread(this,name);
                thread.start();
                flag=true;
            }
            socketChannel.register(worker,SelectionKey.OP_READ);
            worker.wakeup();

        }
        @SneakyThrows
        @Override
        public void run() {
            while (true){
                worker.select();
                Iterator<SelectionKey> iterator = worker.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if (next.isReadable()){
                        ByteBuffer buffer = ByteBuffer.allocate(10);
                        SocketChannel channel = (SocketChannel) next.channel();
                        channel.read(buffer);
                        buffer.flip();
                        log.info(Charset.defaultCharset().decode(buffer).toString());
//                        System.out.println();
                    }
                }
            }
        }
    }
}
