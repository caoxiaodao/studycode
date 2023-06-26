package com.study.demo.netty.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author caonan
 * @createtime 2023/6/20 10:31
 * @Description TODO
 * @Version 1.0
 */
public class A1TestByteBuffer {
    /**
     * bytebuffer--非线程安全
     * 1.分配空间ByteBuffer.allocate(10)
     * 2.写数据
     *  channel.read(bytebuffer,bytebuffer数组)
     *   bytebuffer.put()
     * 3.读数据
     * channel.write(bytebuffer,bytebuffer数组)
     * bytebuffer.get()          position的位置往前移动
     * bytebuffer.get(position)  position的位置不会移动
     * 4.读写模式切换
     * bytebuffer.slip()   position设置为0，切换模式
     * bytebuffer.clear()  position设置为0，切换为写模式
     * bytebuffer.compact()  position位置不发生变化，切换为写模式，数据进行压缩
     *                      需要重新读取可调用slip或者rewind
     * 5.mark和reset
     *      mark标记位置，调用reset回到标记位置；slip或者rewind都会清楚标记
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("channeldemo1.txt","rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (true){
                // buffer写入数据
                int read = channel.read(buffer);
                if (read ==-1){
                    break;
                }
                // flip切换读模式
                buffer.flip();
                while (buffer.hasRemaining()){
                    if (buffer.position()==2){
                        buffer.compact();
                        System.out.println("压缩之后"+buffer.position());
                        buffer.put("8".getBytes());
                        buffer.put("9".getBytes());
                        buffer.flip();
                    }

//                    buffer.flip();
                    // 获取数据
                    System.out.println(Byte.toString(buffer.get()));
                }
                // 切换写模式 clear(),compact(),设置完之后既可以写也可以读取
                // position读取写入的游标  capacity 容量 limit是写入或者读取限制
                // clear之后position 会为0
                // compact之后pos像position不会变化，但是未读取过的数据会向前压缩,想要连着读取需要调用flip将position设置为0
                buffer.clear();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
