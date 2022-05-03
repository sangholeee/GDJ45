package prac1;

public class Gun {

	private String model;
	private int bullet;
	private final int FULL_BULLET = 6;       // final : 상수, 항상 대문자를 사용.
	
	public Gun(String model) {
		
		this.model = model;
		
	}
	
	// 장전
	
	public void reload(int bullet) {
		
		this.bullet += bullet;
		if(this.bullet > FULL_BULLET)
			this.bullet = FULL_BULLET;
		
	}
	
	// 발사
	
	public void shoot() {
		
		if(bullet <=0) {
			System.out.println("총알이 없다.");
			return;
		}
		bullet--;
		System.out.println("빵!  " + bullet + "발 남았다.");
	}
	
	
	// 조회
	
	public void info() {
		
		System.out.println(model + "(" + bullet + ")");
		
	}
	
}
