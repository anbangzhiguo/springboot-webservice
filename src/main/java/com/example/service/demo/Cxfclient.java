package com.example.service.demo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.HashMap;
import java.util.Map;


public class Cxfclient {
    private static String address = "http://localhost:8080/services/Hello?wsdl";

    public static void main(String[] args) {
        test1();
    }

    /**
     * 方式1:使用代理类工厂,需要拿到对方的接口
     */
    public static void test1() {
        try {
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            //添加用户名密码拦截器
            //jaxWsProxyFactoryBean.getOutInterceptors().add(new LoginInterceptor("root","admin"));;
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(AppService.class);
            // 创建一个代理接口实现
            AppService cs = (AppService) jaxWsProxyFactoryBean.create();
            // 数据准备
            HashMap m = new HashMap();
            m.put("name","kdkdkdkdkdkdk");
            String s = cs.sayHello("ssd", "fdfdf", m);

            System.out.println("==============返回结果:" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            objects = client.invoke("getUserName2", "1");
            System.out.println("返回数据:" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
