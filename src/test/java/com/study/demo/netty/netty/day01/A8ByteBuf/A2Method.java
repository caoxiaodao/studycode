package com.study.demo.netty.netty.day01.A8ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author caonan
 * @createtime 2023/7/10 15:36
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class A2Method {
    /**
     * 1.常用方法
     *      write各种类型数据，writeInt一次写入4个字节，不管int数据多大
     *      read
     * 2.扩容规则
     *    写入后数据大小没有超过512，则选择下一个16的整数倍；
     *    写入数据大小超过512，则选择下一个2的n次方（成倍扩展）
     * 3.引用计数法控制内存回收
     *  release计数减1
     *  retain技术加1
     * 4.零copy体现
     *      slice
     *      复制
     *      duplicate（零copy） 追加不会影响原bytebuff,因为他们各自维护自己的读写索引，但是set值因为公用内存空间会影响
     *      copy（深度copy）
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
//        buffer.writeBytes("13344".getBytes(StandardCharsets.UTF_8));
        buffer.writeInt(1);
        buffer.writeInt(2);
        buffer.writeInt(3);
        log.info(buffer.toString());
        log.info("{}",buffer.readInt());
       /* //重复读取
        buffer.markReaderIndex();
        log.info("{}",buffer.readInt());
        buffer.resetReaderIndex();
        log.info("{}",buffer.readInt());*/
      /*  // slice
        ByteBuf slice = buffer.slice();
        log.info("{}", ByteBufUtil.prettyHexDump(buffer));
        log.info("{}", ByteBufUtil.prettyHexDump(slice));
        // slice.writeInt(4); slice的数据因为和原本的数据公用一块内存，所以无法写入新数据，会影响原有数据*/
       /* //复制
        ByteBuf duplicate = buffer.duplicate();
        ByteBuf copy = buffer.copy();
        log.info("duplicate{} {}",duplicate.toString(),ByteBufUtil.prettyHexDump(duplicate));
        log.info("buffer{} {}",buffer.toString(),ByteBufUtil.prettyHexDump(buffer));
        log.info("copy{} {}",copy.toString(),ByteBufUtil.prettyHexDump(copy));
        duplicate.writeInt(4);
        duplicate.setInt(2,4);
        log.info("duplicate{} {}",duplicate.toString(),ByteBufUtil.prettyHexDump(duplicate));
        log.info("buffer{} {}",buffer.toString(),ByteBufUtil.prettyHexDump(buffer));
        log.info("copy{} {}",copy.toString(),ByteBufUtil.prettyHexDump(copy));
*/
        //组合
        ByteBuf buffer1 = ByteBufAllocator.DEFAULT.buffer(5);
        buffer1.writeBytes("123".getBytes(StandardCharsets.UTF_8));
        ByteBuf buffer2 = ByteBufAllocator.DEFAULT.buffer(5);
        buffer2.writeBytes("456".getBytes(StandardCharsets.UTF_8));
        ByteBuf buffer3 = ByteBufAllocator.DEFAULT.buffer(buffer1.readableBytes() + buffer2.readableBytes());
        buffer3.writeBytes(buffer1);
        buffer3.writeBytes(buffer2);
        log.info("buffer1 {} {}", ByteBufUtil.prettyHexDump(buffer1));
        log.info("buffer2 {} {}", ByteBufUtil.prettyHexDump(buffer2));
        log.info("buffer3 {} {}", ByteBufUtil.prettyHexDump(buffer3));
        buffer1.readerIndex(0);
        buffer2.readerIndex(0);
        CompositeByteBuf buffer4 = ByteBufAllocator.DEFAULT.compositeBuffer();
        // true 表示增加新的 ByteBuf 自动递增 write index, 否则 write index 会始终为 0
        buffer4.addComponents(true,buffer1,buffer2);
        log.info("buffer4 {} {}", buffer4.toString(),ByteBufUtil.prettyHexDump(buffer4));


    }
}
