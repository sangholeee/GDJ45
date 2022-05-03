package ex02_loop;

public class Quiz02 {

	public static void main(String[] args) {

		// 2x1 = 2   3x1 = 3   4x1=4   ...   9x1=9
		// 2x2 = 4   3x2 = 6   4x2=8   ...   9x2=18
		// ...
		
		
		
		for (int n = 1; n <= 9; n++) {
			for(int dan = 2; dan <= 9; dan++)
				System.out.print(dan + "x" + n + "=" + (dan * n) + "\t");         // \t : 탭문자 를 사용하면 일정한 간격으로 출력 but 웹상에서는 출력 X
		    System.out.println();
		}
		
	}

}
