package com.example.service.demo;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


@WebService(name = "pesbservice")
public class AppServiceImpl {
    private String code = "";
    private String method = "";
    private ArrayList<Object> argList = new ArrayList<>();


    public String addArgs(Object[] objects){
        argList.clear();
        for (Object object : objects) {
            argList.add(object);
        }

        String res = code+";"+method+";";
        for (Object o : argList) {
            res += o.toString()+";";
        }
        return res;
    }

}