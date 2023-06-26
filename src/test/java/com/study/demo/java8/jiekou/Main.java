package com.study.demo.java8.jiekou;

/**
 * @author caonan
 * @createtime 2023/5/23 16:30
 * @Description TODO
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 接口之前只有抽象方法（abstract修饰），java8增加了默认方法（default），静态方法（static）
         * * 1.默认方法可在子类重写，也可不在子类重写
         *      * 2.如果实现了多个接口，则必须在实现类中重写方法申请是实现了哪个父类的默认方法
         *      * 3.出现默认方法后对于抽象类和接口的思考：抽象类为是不是的问题（属性+行为），接口是有没有的问题（行为）；例如老鹰是不是鸟，和老鹰能不能飞的区别
         *      * @return
         *  静态方法无法通过实体类调用，只能通过接口调用
         */
        Cat cat = new Cat();
        // 默认方法
        System.out.println(cat.drink());
        //静态方法无法通过实体类调用，只能通过接口调用
        System.out.println(Animal.play());

    }
}
