package com.study.demo.expiringmap;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

/**
 * @author caonan
 * @createtime 2023/6/14 10:05
 * @Description TODO
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
         ExpiringMap<String, String> map = ExpiringMap.builder()
                .maxSize(10)
                // 设置过期时间60毫秒
                .expiration(120, TimeUnit.MILLISECONDS)
                 .expirationListener(((key, value) -> test(key,value)))
                .variableExpiration()
                // 设置过期协议
                .expirationPolicy(ExpirationPolicy.CREATED).build();
        map.put("token", "lkj2412lj1412412nmlkjl2n34l23n4");
        // 模拟线程等待61毫秒...
        try {
            Thread.sleep(61);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("token ===> " + map.get("token"));
        try {
            Thread.sleep(61);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("token ===> " + map.get("token"));

    }

    public static void test(Object key,Object vale) {
        System.out.println(key+"======"+vale);
    }
}
