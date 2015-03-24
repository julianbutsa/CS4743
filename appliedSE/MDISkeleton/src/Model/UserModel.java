package Model;

public class UserModel {

	private String username;
	private String email;
	private int permissions;
	
	public UserModel(String u, String e){
		this.username = u;
		this.email=e;	
		this.permissions = 0;
	}
	
	public UserModel(String u, String e, int p){
		this.username = u;
		this.email=e;
		this.permissions = p;
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
	
	
	public int getPermissions(){
		return this.permissions;
	}
	
	public void setPermissions(int p){
		this.permissions = p;
	}
	
}
