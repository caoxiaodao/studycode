package com.study.demo.netty.nio;

import java.nio.ByteBuffer;

import static com.study.demo.netty.nio.ByteBufferUtil.debugAll;

/**
 * @author caonan
 * @createtime 2023/6/20 18:19
 * @Description TODO
 * @Version 1.0
 */
public class A3StickyHalfPack {
    /**
     * 两个如下的bytebuffer按照\n分隔读出
     * - Hello,world\nI'm zhangsan\nHo
     * - w are you?\n
     * @param args
     */
    public static void main(String[] args) {
//        String strA = "123\n456\n78";
//        String strB = "w are you?\\n";
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("12\n34\n5".getBytes());
        test(source);
        source.put("6\n78\n".getBytes());
        test(source);
//        ByteBuffer bufferA = Charset.defaultCharset().encode(strA);
//        ByteBuffer bufferB = Charset.defaultCharset().encode(strB);
//        split(source);
//        split(bufferB);

    }
    public static void test(ByteBuffer buffer){
        buffer.flip();
        int oldLimit = buffer.limit();
        for (int i = 0; i < oldLimit;i++){
            if (buffer.get(i) == '\n'){
                ByteBuffer target = ByteBuffer.allocate(i+1-buffer.position());
                buffer.limit(i+1);
                target.put(buffer);
                String s = new String(target.array());
                System.out.println(s);
                buffer.limit(oldLimit);
                buffer.compact();
            }
        }
    }
    private static void split(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if (source.get(i) == '\n') {
                System.out.println(i);
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source); // 从source 读，向 target 写
                debugAll(target);
//                System.out.println(Charset.defaultCharset().decode(target).toString());
                source.limit(oldLimit);
            }
        }
        source.compact();
    }
}
