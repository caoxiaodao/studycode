package com.study.demo.netty.nio;

/**
 * @author caonan
 * @createtime 2023/7/4 11:42
 * @Description TODO
 * @Version 1.0
 */
public class A10AioServer {
    /**
     * io模型
     *      阻塞：发起读取数据一直线程一直阻塞，直到数据准备完成（等待数据，数据复制到用户空间）
     *    非阻塞: 发起读取数据之后线程立刻返回（当即可能数据并没有准备好，需要循环获取结果直到数据准备完成）
     *    io复用：当存在多个数据读取时，以上两种情况都需要多个线程来处理，有点浪费资源
     *           所以特意启动一个线程（selector），轮询获取多个数据读取结果，待有数据准备完成之后就通知（channel）读取数据
     *    信号驱动：待完成等待数据之后由通知用户程序继续进行后续处理
     *    异步io：待完成等待数据和数据复制两个流程之后通知用户程序继续进行后续处理
     *    示例：
     *          bio  同步阻塞
     *          nio  同步非阻塞
     *          aio  异步非阻塞
     * 零copy：
     *      传统流程：磁盘-DMA->内核缓冲区-->用户缓存区-->socket缓冲区-DMA->网卡
     *      nio优化：内核缓冲区和用户缓冲区公用（DirectByteBuffer和HeapByteBuffer的区别），但是内核态切换次数没有减少
     *      transferto优化：底层采用了 linux 2.1 后提供的 sendFile 方法，减少了一次内核切换
     *      linux2.4优化，内核缓冲区直接写数据到网卡
     * @param args
     */
    public static void main(String[] args) {

    }
}
