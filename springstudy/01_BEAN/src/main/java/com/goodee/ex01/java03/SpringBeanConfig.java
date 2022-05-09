package com.goodee.ex01.java03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	@Bean(name="clac")      // <bean id="calc">
	public Calculator xx() {
		return new Calculator();
	}
	
	
	@Bean(name="gugudan")  // <bean id="gugudan">
	public Gugudan yy() {
		
		return new Gugudan(xx(), 3, 7);
	}
	
	
}
