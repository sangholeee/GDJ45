package ex17_abstract;

public class Main {

	public static void main(String[] args) {

		GameUnit unit1 = new Tank("탱크");
		GameUnit unit2 = new Marine("마린");
		
		System.out.println(unit1.getName() + " 에너지 " + unit1.getEnergy() + " 공격력 " + unit1.getPower());
		System.out.println(unit2.getName() + " 에너지 " + unit2.getEnergy() + " 공격력 " + unit2.getPower());
		
		System.out.println("===전투 시작===");
		
		// 공격 차례
		boolean myTurn = Math.random() < 0.5;
				
		// 두 유닛이 모두 살아있으면 반복
		while(unit1.isAlive() && unit2.isAlive()) {     // (unit1.getEnergy > 0 && unit2.getEnergy > 0)
			if (myTurn) {
				System.out.println(unit1.getName() + "의 공격!");
				unit1.attack(unit2);     // unit1이 unit2를 공격한다.
				myTurn = false;
			} else {
				System.out.println(unit2.getName() + "의 공격!");
				unit2.attack(unit1);
				myTurn = true;
			}
		}
				
		System.out.println("===전투 종료===");
				
		// 승자는?
		if(unit1.isAlive()) {
			System.out.println(unit1.getName() + "의 승리! 남은 에너지 " + unit1.getEnergy());
		} else {
			System.out.println(unit2.getName() + "의 승리! 남은 에너지 " + unit2.getEnergy());
		}
				
		// Tank, Marine 생성자에서 에너지와 공격력을 만들어 주세요.
		// Tank    에너지 : 100, 공격력 : 10
		// Marine  에너지 : 50, 공격력 : 5
				
		// Tank는 5%의 확률로 한 번에 상대를 죽일 수 있다.
		// Marine은 30%의 확률로 한 번에 상대를 죽일 수 있다.		
	}

}
