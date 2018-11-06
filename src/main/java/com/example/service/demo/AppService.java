package com.example.service.demo;


import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.HashMap;

@WebService
public interface AppService {
    @WebMethod
    public String sayHello(String code, String token, HashMap map);
}