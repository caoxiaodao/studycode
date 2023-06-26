package com.study.demo.java8.jiekou;

/**
 * @author caonan
 * @createtime 2023/5/23 16:28
 * @Description TODO
 * @Version 1.0
 */
public class Cat implements Animal,Drink{
    @Override
    public String eat() {
        return null;
    }

    @Override
    public String drink() {
        return Animal.super.drink();
    }
}
