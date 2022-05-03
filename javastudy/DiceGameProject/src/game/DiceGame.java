package game;

public class DiceGame {

	private Player[] players;
	private int idx;
	
	public DiceGame(int n) {
		players = new Player[n];
	}
	
	public void join(Player player) {
		if(idx == players.length) {
		System.out.println("더 이상 참여할 수 없습니다.");
		return;
		}
		players[idx++] = player;
	}
	
	public void play() {
		int i = 0;
		while(true) {
			if(players[i].turn()) {
				System.out.println(players[i].getName() + "님 승리!");
				break;
			} else {
				i++;
				i %= players.length;
			}
		}
	}
	
}
