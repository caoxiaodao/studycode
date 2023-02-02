package com.study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caonan
 * @Date 2022/8/10 11:49
 * @Description
 */
@Controller
@RequestMapping(value = "/ws/rest/")
public class VenustechTJ6071TaskController {
//    userLoginThird
    @RequestMapping(value = "ScannerWS/userLoginThird",method = RequestMethod.POST)
    @ResponseBody
    public Map userLoginThird(String username, String password){
        HashMap<String, Object> res = new HashMap<>();
        res.put("value",200);
        res.put("desc","");
        res.put("third_session_id","1234567");
        return res;
    }
    @RequestMapping(value = "ScannerWS/getPolicyListThird",method = RequestMethod.POST)
    @ResponseBody
    public Map getPolicyListThird(String third_session_id, String policyType){
        HashMap<String, Object> res = new HashMap<>();
        res.put("value",1);
        res.put("desc","");
        ArrayList<Map> objects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("policyID",i);
            map.put("policyName","策略名称"+i);
            map.put("policyType",0);
            map.put("policyDesc","策略名称"+i);
            objects.add(map);
        }
        res.put("policyList",objects);
        return res;
    }
    @RequestMapping(value = "vulntask/createTask",method = RequestMethod.POST)
    public Map getCreateTask(String third_session_id, String policyType){
        HashMap<String, Object> res = new HashMap<>();
        res.put("value",200);
        res.put("desc","");
        ArrayList<Map> objects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("policyID",i);
            map.put("policyName","策略名称"+i);
            map.put("policyType",0);
            objects.add(map);
        }
        res.put("policyList",objects);
        return res;
    }

}
