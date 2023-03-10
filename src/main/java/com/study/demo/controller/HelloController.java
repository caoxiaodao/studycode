package com.study.demo.controller;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String sayHello(@RequestBody byte[] data, @RequestHeader(value = "Topic") String topic, HttpServletResponse response) {

        return "hello";
    }
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello() {
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
