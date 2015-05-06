package Model;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import DBclass.DBQuery;
import userRemote.LoginRemote;
import userRemote.SessionRemote;
public class Authenticator {
	
	public static SessionRemote login(String usr, String pass){
		SessionRemote rs = null;

		try {
			System.out.printf("Trying authentication: %s, %s\n", usr, pass);
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			props.put("org.omg.CORBA.ORBInitialPort", "3700");

			InitialContext itx = new InitialContext(props);
			LoginRemote lr= (LoginRemote) itx.lookup("java:global/Assign6_beans/LoginRemote!userRemote.LoginRemote");
			rs = lr.login(usr, pass);
			if(rs == null){
				System.out.printf("rs was null\n");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return rs;
	}
	
	
	
	
}
