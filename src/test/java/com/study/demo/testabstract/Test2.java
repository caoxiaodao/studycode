package com.study.demo.testabstract;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caonan
 * @createtime 2023/8/23 11:08
 * @Description TODO
 * @Version 1.0
 */
public class Test2 extends AbstractTest{
    public Map test(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("str","test2的方法");
        return map;
    }
    public void generateReport(){
        HashMap<Object, Object> map = new HashMap<>();
        Map<Object, Object> overview = this.test();
        map.put("overview",overview);
        map.put("total",this.getScheduleDetail());
        System.out.println(map);
    }
}
