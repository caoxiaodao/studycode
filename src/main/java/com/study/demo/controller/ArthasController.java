package com.study.demo.controller;

import com.study.demo.service.arthas.TestWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caonan
 * @createtime 2023/4/1 10:50
 * @Description arthas工具使用调用测试
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/arthas")
public class ArthasController {
    @Autowired
    private TestWatch testWatch;
    @RequestMapping(value = "/testMap",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> testMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("wangh","ewangh");
        return testWatch.testMap("str",map);
    }
}
