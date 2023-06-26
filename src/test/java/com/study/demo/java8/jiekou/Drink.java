package com.study.demo.java8.jiekou;

/**
 * @author caonan
 * @createtime 2023/5/23 17:06
 * @Description TODO
 * @Version 1.0
 */
public interface Drink {
    default String drink(){
        return "interface-Drink-drink";
    };
}
