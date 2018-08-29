package com.example.service.demo;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

//name暴露的服务名称, targetNamespace:命名空间,设置为接口的包名倒写(默认是本类包名倒写). endpointInterface接口地址
@Component
@WebService(name = "test" ,targetNamespace ="http://demo.service.example.com/" ,endpointInterface = "com.example.service.demo.AppService")
public class AppServiceImpl implements AppService {

    @Override
    public String sayHello(){
        System.out.println("==============hello=============");
        return "hello";
    }

}