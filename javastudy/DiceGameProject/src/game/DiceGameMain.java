package game;

public class DiceGameMain {

	public static void main(String[] args) {

		
		DiceGame dicegame = new DiceGame(3);
		
		dicegame.join(new Player("호랑이"));
		dicegame.join(new Player("코뿔소"));
		dicegame.join(new Player("도마뱀"));
		
		
		dicegame.play();
		
		
	}

}
