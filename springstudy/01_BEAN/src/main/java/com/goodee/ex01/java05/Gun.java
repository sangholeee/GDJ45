package com.goodee.ex01.java05;

public class Gun {
	
	// field
	private String model;	// 총기모델
	private int bullet;		// 총알수
	
	// getter/setter
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getBullet() {
		return bullet;
	}
	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
	@Override
	public String toString() {
		return "총기모델 : " + model + "\n총알수 : " + bullet;
	}
	

}
