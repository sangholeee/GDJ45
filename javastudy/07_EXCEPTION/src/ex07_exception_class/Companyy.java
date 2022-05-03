/*package ex07_exception_class;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Companyy {

	private String name;    // 회사명
	private Employee[] employees;
	private int idx;
	private Scanner sc;
	
	// 직접
	
	// 생성자, 추가, 삭제, 조회, 전체조회, 메뉴, 관리
	
	// 생성자
	
	public Companyy(String name, int count) {

		this.name = name;
		employees = new Employee[count];
		sc = new Scanner(System.in);
	}
	
	
	// 추가
	
	public void addEmployee() throws RuntimeException {
		if(idx==employees.length)
			throw new RuntimeException("더 이상 추가할 수 없습니다.");
		System.out.print("추가할 직원의 유형을 선택하세요. (1. 정규 2. 임시) >>> ");
		switch(sc.nextInt()) {
		case 1: 
			System.out.print("추가할 직원의 정보를 입력하세요.");
			String name1 = sc.next();
			int empNo1 = sc.nextInt();
			int salary1 = sc.nextInt();
			employees[idx++] = new Regular(empNo1, name1, salary1);
			break;
		case 2:
			System.out.print("추가할 직원의 정보를 입력하세요.");
			String name2 = sc.next();
			int empNo2 = sc.nextInt();
			int salary2 = sc.nextInt();
			employees[idx++] = new Temporary(empNo2, name2, salary2);
			break;
		}
		System.out.println("직원이 추가되었습니다.");
		
	}
	
	// 삭제
	
	public void removeEmployee() {
		if(idx == 0)
			throw new RuntimeException("등록된 직원이 없습니다.");
		
		
	}
	
	// 조회
	
	public void findEmployee() {
		if(idx == 0)
			throw new RuntimeException("등록된 직원이 없습니다.");
		System.out.println("직원 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < idx; i++) {
			if(name.equals(employees[i].getName())) {
				System.out.println("조회된 직원 " + employees[i]);
			    return;
			}
		}
		System.out.println(name + "조회된 직원이 없습니다.");
	}
	
	// 전체조회
	
	public void findAllEmployees() {
		if(idx == 0)
			throw new RuntimeException("등록된 직원이 없습니다.");
		System.out.println("=== 전체 직원 목록 ===");
		for(int i = 0; i < idx; i++) {
			System.out.println(employees[i]);
		}
			
		
		
	}
	
	// 메뉴
	
	public void menu() {
		System.out.println("**************");
		System.out.println("*** 1.추가 ***");
		System.out.println("*** 2.삭제 ***");
		System.out.println("*** 3.조회 ***");
		System.out.println("*** 4.전체 ***");
		System.out.println("*** 0.종료 ***");
		System.out.println("**************");
	}
	
	// 관리
	
	public void manage() {
		while(true) {
			try {
				menu();
				System.out.print("선택(1,2,3,4,0) >>> ");
				switch(sc.nextInt()) {
				case 1: addEmployee(); break;
				case 2: removeEmployee(); break;
				case 3: findEmployee(); break;
				case 4: findAllEmployees(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default : System.out.println("잘못된 선택입니다.");
				}
			} catch (InputMismatchException e) {
				System.out.print("선택은 1,2,3,4,0 중 하나입니다.");
			} catch (RuntimeException e) {
				System.out.print(e.getMessage());
			}
		}
	}

	
}
*/