package ex04_has_a;

public class Main {

	public static void main(String[] args) {

		Tv tv = new Tv();
		
		tv.Chdown();
		tv.Chup();
		
		
		////////////////////////////////
		
		Remocon remocon = new Remocon();
		Radio radio = new Radio(remocon);
		
		radio.Chup();
		radio.Chdown();
		
		
	}

}
