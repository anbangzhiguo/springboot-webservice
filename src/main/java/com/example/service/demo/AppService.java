package com.example.service.demo;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AppService {
    @WebMethod
    public String sayHello();
}