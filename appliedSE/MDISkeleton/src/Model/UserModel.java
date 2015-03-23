package Model;

public class UserModel {

	private String username;
	private String email;
	
	
	public UserModel(String u, String e){
		this.username = u;
		this.email=e;	
	}


	
	//Getters and setters
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
