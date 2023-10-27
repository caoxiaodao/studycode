package com.study.demo.testabstract;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caonan
 * @createtime 2023/8/23 11:06
 * @Description TODO
 * @Version 1.0
 */
public class AbstractTest extends AbstractA{
    public Map getScheduleDetail(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("C","公共的方法");
        return map;
    }
}
