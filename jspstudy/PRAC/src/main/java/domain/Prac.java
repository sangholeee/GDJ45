package domain;

public class Prac {

	public static void main(String[] args) {
		
		int kor = 0;
		String grade = null;
		
		if(kor >= 87) grade = "A";
		else if(kor == 0) grade = "0";
		else grade = "B";
		
		
		System.out.println(grade);
		
	}

}
