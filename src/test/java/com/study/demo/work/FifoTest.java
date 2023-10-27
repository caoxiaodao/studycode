package com.study.demo.work;

import lombok.SneakyThrows;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author caonan
 * @createtime 2023/6/28 10:53
 * @Description TODO
 * @Version 1.0
 */
public class FifoTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
       Thread threadA = new Thread(new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i < 1000; i++) {
                   queue.offer(i);
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       });
       Thread threadB = new Thread(new Runnable() {
           @SneakyThrows
           @Override
           public void run() {
               while (true){
                   Thread.sleep(10);
//                   System.out.println(System.currentTimeMillis());
                   for (int i = 0; i < queue.size(); i++) {
                       System.out.println(queue.poll());
                   }
               }

           }
       });
       threadA.setDaemon(true);
       threadB.setDaemon(true);
       threadA.start();
       threadB.start();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
