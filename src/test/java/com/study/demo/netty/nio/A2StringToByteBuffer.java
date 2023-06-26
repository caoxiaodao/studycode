package com.study.demo.netty.nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author caonan
 * @createtime 2023/6/20 17:57
 * @Description TODO
 * @Version 1.0
 */
public class A2StringToByteBuffer {
    /**
     * 字符串和bytebuffer的转换
     *ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("你好");
     * ByteBuffer buffer2 = Charset.forName("utf-8").encode("你好");
     *
     * debug(buffer1);
     * debug(buffer2);
     *
     * CharBuffer buffer3 = StandardCharsets.UTF_8.decode(buffer1);
     * System.out.println(buffer3.getClass());
     * System.out.println(buffer3.toString());
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer bufferA = Charset.defaultCharset().encode("你好");
        bufferA.flip();
        bufferA.flip();
        System.out.println(Charset.defaultCharset().decode(bufferA));


    }
}
