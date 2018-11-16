package com.example.service.demo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Cxfclient {
    private static String address = "http://localhost:8080/services/Hello2?wsdl";

    public static void main(String[] args) {
        test2();
    }



    /**
     * 动态调用方式
     */
    public static void test2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(address);
        // 需要密码的情况需要加上用户名和密码
       // client.getOutInterceptors().add(new LoginInterceptor("root","admin"));
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            System.out.println("======client"+client);

            objects = client.invoke("sayHello2", "aaaa",new BigDecimal(88.88),getXMLGregorianCalendar(new Date()));
            System.out.println("返回数据:" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static javax.xml.datatype.XMLGregorianCalendar getXMLGregorianCalendar(
            java.util.Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        javax.xml.datatype.DatatypeFactory dtf = javax.xml.datatype.DatatypeFactory
                .newInstance();
        return dtf.newXMLGregorianCalendar(calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH) + 1, calendar
                        .get(calendar.DAY_OF_MONTH), calendar
                        .get(calendar.HOUR), calendar.get(calendar.MINUTE),
                calendar.get(calendar.SECOND), calendar
                        .get(calendar.MILLISECOND), calendar
                        .get(calendar.ZONE_OFFSET)
                        / (1000 * 60));
    }
}
