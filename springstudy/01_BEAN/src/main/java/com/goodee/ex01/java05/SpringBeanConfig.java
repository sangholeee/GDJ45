package com.goodee.ex01.java05;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	
	@Bean(name="gun2")
	public Gun a() {
		Gun gun = new Gun();
		gun.setModel("AK-47");
		gun.setBullet(20);
		return gun;
	}
	
	@Bean(name="soldier2")
	public Soldier b() {
		
		Map<String, String> army = new HashMap<String, String>();
		army.put("name", "이기자");
		army.put("location", "화천");
		
		Soldier soldier = new Soldier(); 
		soldier.setName("박중사");
		soldier.setGun(a());
		soldier.setArmy(army);
		
		return soldier;
		
		// return new Soldier("박중사", gun2(), army);
		}
}
