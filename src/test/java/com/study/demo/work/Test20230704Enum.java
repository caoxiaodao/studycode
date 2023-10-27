package com.study.demo.work;

/**
 * @author caonan
 * @createtime 2023/7/4 11:19
 * @Description TODO
 * @Version 1.0
 */
public class Test20230704Enum {
    enum Color{
        RED,
        Green,
        BlUE
    }
    public static void main(String[] args) {
        Color color  = Color.RED;
        System.out.println(color.name()+"-->"+color.ordinal());
        System.out.println("RED".equals(color));
        System.out.println(Color.RED.equals(color.name()));
    }
}
