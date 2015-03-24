package Model;

import DBclass.DBQuery;

public class Authenticator {
	
	public static Session login(String usr, String pass, DBQuery db){
		UserModel m;
		if((m = db.login(usr,pass)) != null){
			return new Session(m);
		}
		return null;
	}
	
	
	
	
}
