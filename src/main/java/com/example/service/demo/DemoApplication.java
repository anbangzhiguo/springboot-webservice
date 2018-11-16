package com.example.service.demo;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	@Qualifier(Bus.DEFAULT_BUS_ID)
	SpringBus springBus;

	@RequestMapping("/addInterface")
	public String addInterface() throws Exception {

		ClassPool pool = ClassPool.getDefault();
//
		CtClass cc = pool.get("com.example.service.demo.AppServiceImpl");
		cc.setName("com.example.service.demo.AppServiceImpl2");
		CtClass ccStringType = pool.get("java.lang.String");
		CtClass ccStringType2 = pool.get("java.math.BigDecimal");
		CtClass ccStringType3 = pool.get("java.util.Date");
//
//            // 参数：  1：返回类型  2：方法名称  3：传入参数类型  4：所属类CtClass
		CtMethod ctMethod = new CtMethod(ccStringType, "sayHello2", new CtClass[]{ccStringType,ccStringType2,ccStringType3}, cc);
		ctMethod.setModifiers(Modifier.PUBLIC);
		StringBuffer body = new StringBuffer();
		body.append("{");
		body.append("\n    return \"Hello, \" + $proceed($args);");
		body.append("\n}");
		ctMethod.setBody(body.toString(),"this","addArgs");
		cc.addMethod(ctMethod);

		ClassFile ccFile = cc.getClassFile();
		ConstPool constPool = ccFile.getConstPool();
//
//            // 添加类注解
//            AnnotationsAttribute bodyAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
//            Annotation bodyAnnot = new Annotation("javax.jws.WebService", constPool);
//            bodyAnnot.addMemberValue("name", new StringMemberValue("HelloWoldService", constPool));
//            bodyAttr.addAnnotation(bodyAnnot);
//
//            ccFile.addAttribute(bodyAttr);

		// 添加方法注解
		AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
		Annotation methodAnnot = new Annotation("javax.jws.WebMethod", constPool);
		methodAnnot.addMemberValue("operationName", new StringMemberValue("sayHello2", constPool));
		methodAttr.addAnnotation(methodAnnot);

		Annotation resultAnnot = new Annotation("javax.jws.WebResult", constPool);
		resultAnnot.addMemberValue("name", new StringMemberValue("result", constPool));
		methodAttr.addAnnotation(resultAnnot);

		ctMethod.getMethodInfo().addAttribute(methodAttr);

		// 添加参数注解
		ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(
				constPool, ParameterAnnotationsAttribute.visibleTag);
		Annotation paramAnnot = new Annotation("javax.jws.WebParam", constPool);
		paramAnnot.addMemberValue("name", new StringMemberValue("name", constPool));

		Annotation paramAnnot2 = new Annotation("javax.jws.WebParam", constPool);
		paramAnnot2.addMemberValue("name", new StringMemberValue("code", constPool));

		Annotation paramAnnot3 = new Annotation("javax.jws.WebParam", constPool);
		paramAnnot3.addMemberValue("name", new StringMemberValue("method", constPool));
		Annotation[][] paramArrays = new Annotation[3][1];
		paramArrays[0][0] = paramAnnot;
		paramArrays[1][0] = paramAnnot2;
		paramArrays[2][0] = paramAnnot3;
		parameterAtrribute.setAnnotations(paramArrays);

		ctMethod.getMethodInfo().addAttribute(parameterAtrribute);

		//把生成的class文件写入文件
//            byte[] byteArr = cc.toBytecode();
//            FileOutputStream fos = new FileOutputStream(new File("D://AppServiceImpl.class"));
//            fos.write(byteArr);
//            fos.close();

		Class aClass = cc.toClass();
		EndpointImpl endpoint = new EndpointImpl(springBus, aClass.newInstance());
		endpoint.publish("/Hello2");


		return "88888";
	}

}
