package com.study.demo.java8.lamda;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author caonan
 * @createtime 2023/5/23 17:37
 * @Description TODO
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 1.lamda表达式可以替代一部分匿名内部类，让代码变得更加简洁紧凑
         * 2.lamda表达式只能实现函数式接口（一个接口只有一个抽象方法-静态方法和默认方法不算）
         *  2.1 @FunctionalInterface注解是函数式接口的校验
         * 3.表达式语法 详见下面情况
         * 4.lamda表达式相当于是一个匿名内部类，使用的变量必须是final修饰的局部变量
         * 5.lamda表达式在jvm中的应用
         */
        /**
         * 3.1无返回值无参数
         */
        NoParamNoRe noParamNoRe = ()-> System.out.println("无参数无返回值");
        noParamNoRe.test();
        /**
         * 3.2 有参数(一个)无返回值
         */
        ParamNoRe paramNoRe = str1 -> System.out.println("有参数："+str1+"无返回值");
        paramNoRe.test("test");

        /**
         * 3.3 有参数（两个）有返回值
         */
        ParamRe paramRe = (str1, str2) -> {
            return str1+str2;
        };
        System.out.println("有参数有返回值"+paramRe.test("test1","test2"));
        /**
         * 5.1foreach中参数Consumer<? super E> var1是函数式接口
         */
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.forEach(n->{
            System.out.println(n.toString());;
        });
        /**
         * map的merge方法；统计字符串当中某个单词出现的次数
         */
        String str = "adhfjshdlfadjfljihdfkjaa";
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: str.toCharArray()){
            map.merge(c,1,(a,b)->{
                return a+b;
            });
        }
        map.forEach((key,value)->{
            System.out.println(key+"出现了"+value+"次数");
        });
    }
}
