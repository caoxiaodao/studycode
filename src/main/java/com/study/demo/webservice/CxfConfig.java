package com.study.demo.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author caonan
 * @Date 2022/8/12 15:16
 * @Description
 */
@Configuration
public class CxfConfig {
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        return new SpringBus();
    }
    @Bean
    public ServletRegistrationBean disServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/test/*");
    }
    @Autowired
    OfficeNetWebservice officeNetWebservice;

   @Bean
   public Endpoint invoiceEndpoint() {
       EndpointImpl endpoint = new EndpointImpl(springBus(),officeNetWebservice);
       endpoint.publish("/SecurityWebService");
       return endpoint;
   }
}
