package prac1;

public class Ex09 {

	public static void main(String[] args) {

		BankAccount me = new BankAccount("1234", 50000);
		BankAccount mom = new BankAccount("5678", 100000);
		
		me.deposit(50000);
		
		me.inquiry();
		mom.inquiry();
		
		
	}

}
