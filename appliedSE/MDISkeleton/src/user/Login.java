package user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import userRemote.LoginRemote;
import userRemote.SessionRemote;

@Stateless(name="LoginRemote")
public class Login implements LoginRemote{

	@PersistenceContext
	EntityManager em;
	@Override
	public SessionRemote login(String uname, String pw) {
		
		SessionRemote rs = null;
		/*
		// TODO Auto-generated method stub
		User u = em.find(User.class, uname);
		if(pw.equals(u.getPassword())){
			rs = new Session();
			UserBean ub = new UserBean();
			ub.setFullname( u.getFullname());
			ub.setEmail(u.getEmail());
			ub.setId(u.getId());
			rs.setUser(ub);
			rs.setPermissions(u.getPermissions());
		}*/
		
		if(uname.equals("test") && pw.equals("1111")){
			rs = new Session();
			rs.setPermissions(3);
			UserBean ub = new UserBean();
			ub.setFullname( "Test Guy");
			ub.setEmail("Test@Bean.com");
			ub.setId(0);
			rs.setUser(ub);
		}
		return rs;
	}

	@Override
	public boolean register(String username, String email, String password) {
		// TODO Auto-generated method stub
		if(em.find(User.class, username) != null){
			return false;
		}
		
		
		return true;
	}
	
}
