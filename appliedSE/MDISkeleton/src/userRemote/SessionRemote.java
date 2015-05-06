package userRemote;

//import user.UserBean;

public interface SessionRemote {

	public UserBeanRemote getUser();
	public void setUser(UserBeanRemote ub);
	
	public int getPermissions();
	public void setPermissions(int p);
}
