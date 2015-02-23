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

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import assignment1.ItemModel;
import assignment1.PartModel;


public class DBQuery {
	
	public Connection myConnection;
	public String url = 		"jdbc:mysql://devcloud.fulgentcorp.com:3306/jbc878";
	public String user =		"jbc878";
	public String password = 	"Ril6gl1LglxhUyQAdq6H";
	
	
	public DBQuery(){

		//connect to the database
		DataSource source = getDataSource();
		try {
			this.myConnection = source.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<PartModel> getParts(){
		ArrayList<PartModel> returnList = null;
		String query = "select * from part";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<PartModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				PartModel temp = new PartModel( rs.getString("pname"), rs.getString("pnum"), 
												rs.getString("vendor"), rs.getString("pnumext") );
				temp.setId(rs.getInt("pid"));
				returnList.add(temp);
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
	public ArrayList<ItemModel> getInventory(){
		ArrayList<ItemModel> returnList = null;
		String query = "select * from inventory join location on (inventory.lid = location.lid)";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<ItemModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				PartModel tpart = this.getPart(rs.getInt("pid"));
				ItemModel m = new ItemModel(tpart, rs.getString("lname"), rs.getInt("qty"));
				m.setId(rs.getInt("invid"));
				returnList.add(m);
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
	
	public ArrayList<String> getLocations(){
		ArrayList<String> returnList = null;
		String query = "select lname from location";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<String>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				returnList.add(rs.getString("lname"));
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
	
	public PartModel getPart(int pid){
		String query = "select * from part where pid = " + pid;
		PartModel p = null;
		try{
			Statement s = myConnection.createStatement();
			ResultSet r = s.executeQuery(query);
			if(r.next()){
				p = new PartModel(r.getString("pname"), r.getString("pnum"), 
						r.getString("vendor"), r.getString("pnumext"));
				p.setId(r.getInt("pid"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public ItemModel getItem(){
		return null;
	}
	
	public int getLocationId(String l){
		int lid = 0;
		String query = "select lid from location where lname = \""+l+"\"";
		try{
			Statement s = myConnection.createStatement();
			ResultSet r =  s.executeQuery(query);
			if(r.next()){
				lid = r.getInt("lid");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lid;
	}
	
	
	public void addPart(PartModel p){
		
		//insert into part table
		String query = "insert into part (pname, pnum, pnumext, unit, vendor) "
				+ "values (\""+p.getPname()+"\", "+p.getPnum()+", "+p.getExternal()+",\""+p.getQunit()+"\",\""+p.getVendor()+"\")";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = s.getGeneratedKeys();
			if(rs.next()){
				int pid = rs.getInt(1);
				p.setId(pid);
			}
			
			
			//this.addToInventory(p);
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void addToInventory(ItemModel i){
		int pid = i.getPart().getId();
		int lid = this.getLocationId(i.getLocation());
		String query = "insert into inventory (pid, lid, qty) "
				+ "values (\""+pid+"\", "+ lid +", "+i.getQuantity()+")";
		try{
			Statement s = myConnection.createStatement();
			int iid = s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			i.setId(iid);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void addLocation(String lname){
		
		String query = "insert into location (lname) values (\""+lname+"\")";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 * delete function 
	 */
	
	public void deletePart(PartModel p){
		this.deleteInventoryEntry(p.getId());
		String query = "delete from part where pid = "+p.getId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void deleteInventoryEntry(ItemModel i){
		String query = "delete from inventory where pid = " + i.getId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void deleteInventoryEntry(int pid){
		String query = "delete from inventory where pid = "+pid;
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/* 
	 * Update functions
	 */
	public void updatePart(PartModel p){
		PartModel old = this.getPart(p.getId());
		boolean first = true;
		//insert into part table
		String query = "update part set ";
		if(!old.getPname().equals(p.getPname())){
			query += "pname = \""+p.getPname()+"\"";
			if(first){
				first = false;
			}
		}
		if(!old.getPnum().equals(p.getPnum())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query += " pnum =\""+p.getPnum()+"\"";
		}
		if(!old.getExternal().equals(p.getExternal())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query += "pnumext= \""+p.getExternal()+"\"";
		}
		if(!old.getVendor().equals(p.getVendor())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query +="vendor= \""+p.getVendor()+"\"";
		}
		if(!old.getQunit().equals(p.getQunit())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query += "unit= \"" + p.getQunit() +"\"";
		}
		
		query +=" where pid = " +p.getId();
		//System.out.println(query);
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void updateInventory(ItemModel i){
		/*
		String query = "SET foreign_key_checks = 0 ";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}
		*/
		System.out.println(i.getLocation() );
		String query =  "update ignore inventory set pid = "+i.getPart().getId()+", lid ="+this.getLocationId(i.getLocation()) +", qty = " + i.getQuantity() +" where invid ="+ i.getId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}		
		/*
		query = "SET foreign_key_checks = 1";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}*/
	}
	
	
	private DataSource getDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
        try {
        	fis = new FileInputStream("db.properties");
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
        	MysqlDataSource mysqlDS = new MysqlDataSource();
        	mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
        	mysqlDS.setUser(props.getProperty("MYSQL_DB_USER"));
        	mysqlDS.setPassword(props.getProperty("MYSQL_DB_PW"));
        	return mysqlDS;
        } catch(RuntimeException e) {
            e.printStackTrace();
            return null;
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
