package com.study.demo.controller;

import com.study.demo.entity.UserEntity;
import com.study.demo.service.UserService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String sayHello(@RequestParam("username") String username, HttpServletResponse response) {
        return "hello";
    }
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello() {
        return "hello";
    }
    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public String sayHello2() {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://127.0.0.1:8080/test/SecurityWebService/SecurityCommand?wsdl");
        Object[] result;
        QName qName = new QName("http://webservice.demo.study.com/","SecurityCommand");
        try {
            result = client.invoke(qName,"1","2","1","2","1","2",1,1L,1,1,1,"1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }
    @RequestMapping(value = "/auth/analysisToken",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> analysisToken(@RequestParam("token") String token) {
        HashMap<String, Object> res = new HashMap<>();
        if ("123".equals(token)){
            res.put("success",true);
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId","3b3fd714fe8e47ca8dc3657cacc988ca");
            map.put("sn","T0300295");
            map.put("orgId","fa697a079421488f902d854d08321845");
            res.put("data",map);
        }else{
            res.put("success",false);
            HashMap<String, Object> map = new HashMap<>();
            res.put("msg","Token解析失败/失效");
        }
        return res;
    }
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    @ResponseBody
    public UserEntity getUserById(@RequestParam("id") Long id) {
        UserEntity user = userService.findById(id);
        return user;
    }
}
