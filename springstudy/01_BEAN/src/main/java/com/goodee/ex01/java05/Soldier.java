package com.goodee.ex01.java05;

import java.util.Map;

public class Soldier {
	
	// field
	private String name;				// 이름
	private Gun gun;				    // 총기
	private Map<String, String> army;   // 부대명 + 부대위치
	
	// getter/setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gun getGun() {
		return gun;
	}
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	public Map<String, String> getArmy() {
		return army;
	}
	public void setArmy(Map<String, String> army) {
		this.army = army;
	}
	
}
