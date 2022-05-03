package ex03_instance_array;

public class Member {
    
	
	// field
	private String userID;
	private String name;
	
	
	// constructor
    public Member() {
		
		System.out.println();
	}

	public Member(String userID, String name) {
		super();
		this.userID = userID;
		this.name = name;
	}

	
	
	// method
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
