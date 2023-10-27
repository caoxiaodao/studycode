package com.study.demo.netty.netty.day01.A8ByteBuf;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caonan
 * @createtime 2023/7/10 15:21
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A1Create {
    /**
     * 池化
     * -Dio.netty.allocator.type={unpooled|pooled}
     * 4.1以后默认启用池化实现，4.1之前因为池化功能不成熟，所以默认是非池化实现
     * 直接内存/堆内存
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        log.info("默认"+buffer);// 池化直接内存
        // 堆内存
        ByteBufAllocator.DEFAULT.heapBuffer();
        // 直接内存
        ByteBufAllocator.DEFAULT.directBuffer();

        ByteBuf buffer1 = UnpooledByteBufAllocator.DEFAULT.buffer();
        log.info("非池化"+buffer1);
        ByteBuf buffer2 = PooledByteBufAllocator.DEFAULT.buffer();
        log.info("池化"+buffer2);

    }
}
