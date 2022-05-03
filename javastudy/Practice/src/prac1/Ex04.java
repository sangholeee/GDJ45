package prac1;

import java.util.Scanner;

public class Ex04 {

	public static void main(String[] args) {

		// 입력 받은 정수를 배열의 길이로 사용하기.
		Scanner sc = new Scanner(System.in);
		
		System.out.println("몇 개 생성할까요? >>>");
		int count = sc.nextInt();
		
		if(count < 1 || count > 100) {
			System.out.println("1~100 사이만 가능합니다.");
			return;       // 반환 타입이 void 일 때만 사용 가능. 해당 메소드를 종료.
		}
		
		int[] arr = new int[count];
		
		// 배열 arr에 난수(1~100) 저장
		// 중복 생성되면 재생성
		for(int i = 0; i < arr.length; i++) {
			int random = (int)(Math.random() * 100) + 1;
			
			// random 검사 메소드
			// 중복이 있으면 true 반환, 중복이 없으면 false 반환
			// exists(배열 arr, 인덱스, 난수)
			if(exists(arr, i, random)) {
				i--;          // 그래서 i--를 넣어준다. 
				continue;     // 저장이 안되고 for 문으로 가서 인덱스 값만 증가함, 중복이 발생하면 0이 채워짐.
			}
			
			arr[i] = random;
			
			
		}
		
		// 배열 arr의 출력
		// 한 줄에 10개씩
		
		for(int i = 0; i < arr.length; i++) {
			if(i != 0 && i % 10 == 0)
				System.out.println();
			
			System.out.print(arr[i] + "\t");
			
			
		}
		
	}    // main
	
	public static boolean exists(int[] arr, int idx, int random) {
		
		// 배열 arr의 인덱스 0부터 idx까지 random이 존재하는가?
		
		for(int i = 0; i <= idx ; i++)
			if(arr[i] == random)
				return true;
		
		return false;        // for 문이 끝나는 경우
		
		
	}
	
	
	
	
	
	
	

}
