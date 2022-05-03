package ex03_instance_array;

public class MemberMain {

	// static 메소드는 static 메소드만 호출할 수 있다.
	
	
	public static void m1() {
		Member member1 = new Member();
		member1.setUserID("admin");
		member1.setName("관리자");
		
		Member member2 = new Member("apple", "사과");
		
		System.out.println(member1.getUserID());
		System.out.println(member1.getName());

		System.out.println(member2.getUserID());
		System.out.println(member2.getName());
	}
	
	public static void m2() {
		
		Member[] members = new Member[2];
		
		// members[0] = new Member();
		// members[1] = new Member();
		
		for(int i = 0; i < members.length; i++) {
			members[i] = new Member();            // 중요.
			members[i].setUserID("user" + i);
			members[i].setName("회원" + i);
			}
		
		for(int i = 0; i < members.length; i++)
			System.out.println(members[i].getUserID() + "," + members[i].getName());
		
	}
	
	public static void m3() {
		
		Member[] members = new Member[3];
		String[] ids = {"apple", "banana", "corn"};
		String[] names = {"사과", "바나나", "옥수수"};
		
		for(int i = 0; i < members.length; i++) {
			members[i] = new Member(ids[i], names[i]);
			
		}
		
		for(Member member : members)
			System.out.println(member.getUserID() + "," + member.getName());
		
		
		
		
	}
	
	public static void main(String[] args) {
		m3();
		
	}

}
