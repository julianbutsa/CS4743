package user;

import java.io.Serializable;

import userRemote.UserBeanRemote;

public class UserBean implements UserBeanRemote,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String name;
	private String email;
	
	public UserBean(){
		id = 0;
		name = "";
		email  = "";
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
		
	}

	@Override
	public String getFullname() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setFullname(String fullname) {
		// TODO Auto-generated method stub
		this.name = fullname;
		
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
		
	}

}
