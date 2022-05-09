package com.goodee.ex01.java05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("java/soldier_context.xml");
		
		Soldier soldier1 = ctx.getBean("soldier1", Soldier.class);
		System.out.println("이름 : " + soldier1.getName());
		System.out.println(soldier1.getGun());
		System.out.println("부대명 : " + soldier1.getArmy().get("name"));
		System.out.println("부대위치 : " + soldier1.getArmy().get("location"));
		System.out.println();
		
		Soldier soldier2 = ctx.getBean("soldier2", Soldier.class);
		System.out.println("이름 : " + soldier2.getName());
		System.out.println(soldier2.getGun());
		System.out.println("부대명 : " + soldier2.getArmy().get("name"));
		System.out.println("부대위치 : " + soldier2.getArmy().get("location"));
		
		ctx.close();

	}

}
