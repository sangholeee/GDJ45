package phone;

import java.util.Scanner;

public class PhoneBook {

		private Scanner scanner;
		private Phone[] pArray;
		
		public PhoneBook() {
		    scanner = new Scanner(System.in);
		}
		
		private void input () {
			System.out.print("인원수 >> ");
			int count = scanner.nextInt();
			pArray = new Phone[count];
			
			for(int i = 0; i < count; i++) {
			        System.out.print("이름과 전화번호(이름과 번호는 빈 칸 없이 입력) >> ");
			        String name = scanner.next();
			        String tel = scanner.next();
			
			
	        		// 1. 생성자
	        		pArray[i] = new Phone(name, tel);
			
		        	/*
		        	// 2. setter
		        	pArray[i] = new Phone();
		        	pArray[i].setName(name);
		        	pArray[i].setTel(tel);
		        	*/
			
			}
			System.out.println("저장되었습니다.");
		}
		
		private String search (String name) {
			for(int i = 0; i < pArray.length; i++) {
				if(name.equals(pArray[i].getName()))
					return pArray[i].getTel();
			}
			return null;           // 찾다가 없으면 for문 종료
			
		}
		
		public void run() {
			input();
			while(true) {
				System.out.println("검색할 이름 >> ");
				String name = scanner.next();
				if(name.equals("exit"))  {        // if(name.equalsIgnoreCase("exit") {    -> 대소문자 구분 X
					System.out.println("프로그램 종료!");
				    return;                      //  -> run 메소드 종료. break; -> while(true)의 종료.
				
			        }
			        String tel = search(name);
			        if(tel == null)
				            System.out.println(name + "이 없습니다.");
			        else
				            System.out.println(name + "의 번호는 " + tel + "입니다.");
			}
		}
		
}
	


