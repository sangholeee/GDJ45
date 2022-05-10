package com.goodee.ex01.java06;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// java/customer_context.xml에 만들어져 있는 bean을 가져오세요
@ImportResource("java/customer_context.xml")

@Configuration
public class SpringBeanConfig {
	
	@Bean(name="bank1")
	public BankAccount a() {
		// default constructor + setter injection
		BankAccount bank = new BankAccount();
		bank.setAccNo("110-845-218531");
		bank.setBalance(90000L);
		return bank;
	}
	
	@Bean(name="customer1")
	public Customer b() {
		// default constructor + setter injection
		Customer customer = new Customer();
		customer.setName("영숙");
		Map<String, String> info = new HashMap<String, String>();
		info.put("tel", "010-5548-8954");
		info.put("grade", "vip");
		customer.setInfo(info);
		customer.setBankAccount(a());
		return customer;
	}
}
