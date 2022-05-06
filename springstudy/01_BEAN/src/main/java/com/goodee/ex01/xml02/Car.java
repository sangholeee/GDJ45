package com.goodee.ex01.xml02;

public class Car {

	// field
	private String model;     // xDrive_40d
	private int price;        // 13140
	private Engine engine;	  

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void info() {
		System.out.println("모델명 : " + model);
		System.out.println("가격   : " + price + "만원");
		System.out.println("연료   : " + engine.getFuel());
		System.out.println("연비   : " + engine.getEfficency() + "km/L");
		System.out.println("출력   : " + engine.getHp());
		System.out.println("배기량 : " + engine.getCc());
	}
}
