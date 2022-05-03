package ex02_loop;

public class Ex03_nested_for {

	public static void main(String[] args) {

		// 1일차 1교시 수업입니다.
		// 2일차 2교시 수업입니다.
		// ...
		// 3일차 8교시 수업입니다.
		
		for(int day = 1; day <= 3; day++)
			for(int hour = 1; hour <= 8; hour++)
				System.out.println(day + "일차" + hour + "교시 수업입니다.");
		
		// 문제. 2단부터 9단까지 모두 출력하기
		// 각 단 사이에 ------------- 출력하기
		
		for(int dan = 2; dan <= 9; dan++) {                                //  1. dan = 2                             6. dan = 3 
			for(int n = 1; n <= 9; n++)                                    //  2. n = 1                4. n = 2~9     7. . . .
				System.out.println(dan + "x" + n + "=" + dan * n);         //  3. system.out 2x1=2
		    System.out.println("-------");                                 //  5. system.out ------
		}
				
		
	}

}
