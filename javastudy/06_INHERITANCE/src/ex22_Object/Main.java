package ex22_Object;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		User user1 = new User("admin", "123456");
		User user2 = new User("admin", "123456");
		
		if(user1.equals(user2))                  // 메모리 위치 비교
			System.out.println("동일한 회원");
		else
			System.out.println("다른 회원");
		
		String strUser1 = user1.toString();
		System.out.println(strUser1);
		
		String strUser2 = user2.toString();
		System.out.println(strUser2);
				
		// toString 메소드는 생략할 수 있다.
		System.out.println(user1);
		System.out.println(user2);
		
		User[] users = new User[2];
		users[0] = user1;
		users[1] = user2;
		
		System.out.println(Arrays.toString(users));
	
	}

}
