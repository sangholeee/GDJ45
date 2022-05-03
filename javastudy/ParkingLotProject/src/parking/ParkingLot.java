package parking;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ParkingLot {
	
	private String name;
	private List<Car> cars;
	private Scanner sc;
	
	public ParkingLot(String name) {
		this.name = name;
		cars = new ArrayList<Car>();
		sc = new Scanner(System.in);
	}

	public void addCar() {
		System.out.println("===차량 추가하기===");
		System.out.print("차량번호 >>> ");
		String carNo = sc.next();
		System.out.print("모델 >>> ");
		String model = sc.next();
		cars.add(new Car(carNo, model));
	}
	
	public void deleteCar() throws RuntimeException {
		System.out.println("===차량 삭제하기===");
		System.out.print("제거할 차량번호 >>> ");
		String carNo = sc.next();
		for(int i = 0, size = cars.size(); i < size; i++) {
			Car car = cars.get(i);
			if(carNo.equals(car.getCarNo())) {
				System.out.println(carNo + " 차량이 삭제되었습니다.");
				cars.remove(i);
				return;
			}
		}
		throw new RuntimeException("대상 차량이 존재하지 않습니다.");
	}
	
	public void printAllCars() {
		System.out.println("===전체 조회하기===");
		System.out.println(name + " 차량 목록");
		if(cars.isEmpty())
			throw new RuntimeException("주차장에 등록된 차량이 없습니다.");
		for(Car car : cars)
			System.out.println(car);
	}
	
	public void manage() {
		while(true) {
			try {
				System.out.print("1.추가 2.삭제 3.전체 0.종료 >>> ");
				int choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1: addCar(); break;
				case 2: deleteCar(); break;
				case 3: printAllCars(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default : throw new RuntimeException("존재하지 않는 메뉴입니다.");
				}
			} catch (InputMismatchException e) {
				sc.next();        
				System.out.println("메뉴 입력은 정수입니다.");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
