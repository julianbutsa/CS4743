package userRemote;

import javax.ejb.Remote;



@Remote
public interface LoginRemote {

	public SessionRemote login(String uname, String pw);
	public boolean register(String username, String email, String password);
}
