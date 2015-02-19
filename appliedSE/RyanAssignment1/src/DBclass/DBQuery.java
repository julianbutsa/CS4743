/*
 * Structure of the tables
 * use the table indexes to get specific information in a general query
 * 
 * 
 * Part									inventory
 * --------------						--------------
 * 1: pid		int primary key			1. invid		int primary key
 * 2: pnum		int unique				2. pid			int foreign key
 * 3: pname		string 					3. lid			int foreign key
 * 4: vendor	string					4. qty			int
 * 5: unit		string
 * 
 * location
 * -----------
 * 1. lid		int primary key
 * 2. lname		string
 */

package DBclass;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import assignment1.PartModel;


public class DBQuery {
	
	public Connection myConnection;
	public String url = 		"jdbc:mysql://devcloud.fulgentcorp.com:3306/jbc878";
	public String user =		"jbc878";
	public String password = 	"Ril6gl1LglxhUyQAdq6H​";
	
	
	public DBQuery(){

		//connect to the database
		MysqlDataSource source = new MysqlDataSource();
		try {
			source.setURL("jdbc:mysql://devcloud.fulgentcorp.com:3306/jbc878");
			source.setUser("jbc878");
			source.setPassword("Ril6gl1LglxhUyQAdq6H​");		
			this.myConnection = source.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<PartModel> getParts(){
		ArrayList<PartModel> returnList = null;
		String query = "select * from part join inventory on part.pid = inventory.pid join location on inventory.lid = location.lid";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<PartModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				System.out.println(rs.toString());
				//PartModel temp = new PartModel(	rs.getInt("pid"), Integer.toString(rs.getInt("pnum")), rs.getString("pname"),
					//							rs.getString("vendor"), "",rs.getInt("qty"), rs.getString("unit"), rs.getString("lname")  );
				//returnList.add(temp);
			}
			
			
			//close that stuff in case it got opened.
			rs.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return returnList;
	}
	
	public void addPart(PartModel p){
		//insert into part table
		
		
		//insert into inventory table
		
	}
	
	public void addInventory(){
		
	}
	
	public void addLocation(String lname){
		
		String query = "insert into location (lname) values (" +lname+ ")";
		try{
			Statement s = myConnection.createStatement();
			s.executeQuery(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void destroy(){
		try {
			this.myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
