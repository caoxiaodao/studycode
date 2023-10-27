package com.study.demo.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author caonan
 * @createtime 2023/6/28 19:17
 * @Description TODO
 * @Version 1.0
 */
public class A4TestChannel {
    /**
     * FileChannel 工作在阻塞模式下
     * 创建方式：
     * 1.FileInputStream,FileOutputStream,RandomAccessFile
     * 其中RandomAccessFile提供一个随机访问文件任何位置的方式
     * 2.常用方法
     * write 写入channel
     * read  读取channel
     * close 关闭
     * size 获取文件大小
     * position 获取当前位置，或者设置当前位置
     * force 强制写入
     * *** transferTo复制数据，零拷贝技术（在于操作系统的支持）
     * *** 一次传输文件大小有限制2G/4G（取决于操作系统和文件系统，不好确定需要经过测试进行确定）
     */
    public static void main(String[] args) throws Exception {
        FileChannel fromChannel = new FileInputStream("file/from.txt").getChannel();
        FileChannel toChannel = new FileOutputStream("file/to.txt").getChannel();
        // 请求值  传输数据的开始位置，传输数据的大小，目标fileChannel
        // 返回值 具体一次性传输的字节数
//        long l = 0;
//        long begin = 0;
//        do {
//            l = fromChannel.transferTo(begin, 2, toChannel);
//            begin +=l;
//        } while (l > 0);
        long left = fromChannel.size();
        for (left = fromChannel.size();left>0;){
            left-=fromChannel.transferTo(fromChannel.size()-left,left,toChannel);
        }
    }
}
