package DBclass;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class StartupDB {

	public static Connection connect(String url, String user, String password){
		MysqlDataSource source = new MysqlDataSource();
		source.setURL(url);
		source.setUser(user);
		source.setPassword(password);
		Connection c = null;
		try {
			c = source.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	
	}
}
