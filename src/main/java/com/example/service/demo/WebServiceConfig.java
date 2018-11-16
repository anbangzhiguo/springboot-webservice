package com.example.service.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


@Configuration
public class WebServiceConfig {


    @Bean
    public ServletRegistrationBean disServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/services/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

//    @Bean
//    public Endpoint endpoint() {
//
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = null;
//        try {
//            cc = pool.get("com.example.service.demo.AppServiceImpl");
//            CtMethod sayHello = cc.getDeclaredMethod("sayHello");
//            sayHello.insertAt(0," { code = \"qqqqq\"; }");
//            sayHello.setName("ttt");
//            cc.toClass();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        EndpointImpl endpoint = new EndpointImpl(springBus, new AppServiceImpl());
//        endpoint.publish("/Hello");
//        return endpoint;
//    }
}
