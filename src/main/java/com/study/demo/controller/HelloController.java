package com.study.demo.controller;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

@Controller
public class HelloController {
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
}
