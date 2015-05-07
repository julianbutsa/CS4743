package user;

import java.io.Serializable;

import userRemote.SessionRemote;
import userRemote.UserBeanRemote;

public class Session implements SessionRemote, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int permissions;
	private UserBeanRemote user;

	
	
	public Session(){
		permissions = -1;
		setUser(new UserBean());
	}

	@Override
	public UserBeanRemote getUser() {
		// TODO Auto-generated method stub
		return user;
	}
	@Override
	public void setUser(UserBeanRemote ub) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getPermissions() {
		// TODO Auto-generated method stub
		return permissions;
	}
	@Override
	public void setPermissions(int p) {
		// TODO Auto-generated method stub
		permissions = p;
		
	}
	

	
}
