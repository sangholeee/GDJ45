package ex01_primitive_array;

public class Ex04_array_copy {

	public static void main(String[] args) {

		// 배열의 길이 조정하기
		// 1. 배열의 길이는 변경할 수 없다.
		// 2. 길이가 다른 새로운 배열을 만들고, 값을 옮기고, 참조값을 수정한다.
		
		
		// 배열의 길이가 5인 배열을 생성한 뒤, 
		// 길이가 10인 배열로 변경하기.
		
		int[] arr = new int[5];                // 123번지
		
		for(int i = 0; i < arr.length; i++)
			arr[i] = (i + 1);
		
		// 길이가 10인 새로운 배열 만들기
		int[] temp = new int[10];              // 456번지
		
		// arr -> temp (배열 복사)
		
		/*
		temp[0] = arr[0];		
     	temp[1] = arr[1];		
		temp[2] = arr[2];		
		temp[3] = arr[3];		
		temp[4] = arr[4];		
		*/
		
		for(int i = 0; i < 5; i++)    // 5 -> arr.length
			temp[i] = arr[i];
		
		// 참조값 변경
		arr = temp;                             // 123번지 memory leak, 123번지는 계속해서 남아있다.
		                                        // system.gc(); garbage collector 
		
		
		// 확인
		for(int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
		
		
	}

}
