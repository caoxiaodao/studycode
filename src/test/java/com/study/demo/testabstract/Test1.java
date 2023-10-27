package com.study.demo.testabstract;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caonan
 * @createtime 2023/8/23 11:08
 * @Description TODO
 * @Version 1.0
 */
public class Test1 extends AbstractTest  {
    public Map<Object, Object> test(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("str","test1的方法");
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
