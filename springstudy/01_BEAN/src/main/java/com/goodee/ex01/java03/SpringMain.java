package com.goodee.ex01.java03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;



public class SpringMain {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		
		Gugudan gugudan = (Gugudan)ctx.getBean("gugudan");
		gugudan.printGugudan();
		
		ctx.close();
	}

}
